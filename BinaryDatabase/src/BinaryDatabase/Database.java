package BinaryDatabase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import BinaryDatabase.Database.Table;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Database {
    static class Column  implements Serializable{
        String name;
        String type;		
    }

    static class Table implements Serializable  {
        String name;
        int num_columns;
        Column[] columns = new Column[10];
		ColumnCounts counts ;
    }

    static class Record  implements Serializable{
        int id;
        String[] values = new String[10];
    }

    static class Index  implements Serializable{
        long id;
        long offset;
    }
    static class ColumnCounts implements Serializable{
    	int countChar;
    	int countInt;
    }
    
    static class SearchResult {
        public Index index;
        public boolean found;
        
        public SearchResult(Index index, boolean found) {
            this.index = index;
            this.found = found;
        }
    }
    
    static int INT_SIZE = 4; 
    static int CHAR_SIZE = 20;     
    
    public static void sortArrayByColumnDescending(Object[][] arr, int column) {
        Arrays.sort(arr, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                if (o1[column] instanceof String && o2[column] instanceof String) {
                    return ((String) o2[column]).compareTo((String) o1[column]);
                } else if (o1[column] instanceof Integer && o2[column] instanceof Integer) {
                    return ((Integer) o2[column]).compareTo((Integer) o1[column]);
                } else {
                    throw new IllegalArgumentException("Can't compare columns of different types.");
                }
            }
        });
    }
    
    public static void sortArrayByColumnAscending(Object[][] arr, int column) {
	    Arrays.sort(arr, new Comparator<Object[]>() {
	        @Override
	        public int compare(Object[] o1, Object[] o2) {
	            if (o1[column] instanceof String && o2[column] instanceof String) {
	                return ((String) o1[column]).compareTo((String) o2[column]);
	            } else if (o1[column] instanceof Integer && o2[column] instanceof Integer) {
	                return ((Integer) o1[column]).compareTo((Integer) o2[column]);
	                }
	             else {
	                throw new IllegalArgumentException("Can't compare columns of different types.");
	            }
	        }
	    });
	}
    
    public static Object[][] OrderListByIndex(String tablename, int secim, String order) throws IOException {
    	Scanner scanner = new Scanner(System.in);
    	Table xTable = new Table();

    	xTable.name = tablename;
    	String fileName = xTable.name ; 
    	int countOfDataById=-1;
    	xTable=getTable(fileName);
        
        int numInttype=xTable.counts.countInt;
	    int numChartype=xTable.counts.countChar;
    	int columnCount=numInttype+numChartype;
    	try (RandomAccessFile indexFile = new RandomAccessFile(fileName + "Index.bin", "r")) {
    		indexFile.seek(indexFile.length()-16);
    		countOfDataById=(int)indexFile.readLong();
    		
    	} catch(IOException e) {
    		
    		 e.printStackTrace();		
    	}
    	
    	Object[][] data = new Object[countOfDataById][columnCount];
    	
    	try (RandomAccessFile raf = new RandomAccessFile(fileName+"Records.bin", "r")) {
    		raf.seek(0);
    		for(int i =0;i<countOfDataById;i++) {
    			for(int j=0;j<columnCount;j++) {
    				if(xTable.columns[j].type.equals("int")) {
    					int value=raf.readInt();
    					data[i][j]=value;		
    				}
    				else if(xTable.columns[j].type.equals("char")) {
    					byte[] veri = new byte[CHAR_SIZE]; 
                        int okunanBaytSayisi = raf.read(veri); 
                        String value =new String(veri, 0, okunanBaytSayisi, StandardCharsets.UTF_8);
                        data[i][j]=value.replaceAll("\\p{C}", "");	
    				}   				
    			}    			
    		}    				
    	} catch(IOException e) { 		
   		 e.printStackTrace();		
   	    }
    	int c = secim;
    	
    	if(order.equals("descending"))
    	{
    		sortArrayByColumnDescending(data,c);
    		System.out.println("Offsett");
    	}
    	else if(order.equals("ascending"))
    	{
    		sortArrayByColumnAscending(data,c);
    	}
		 
		return data;	
    }
    
    public static Object[][] OrderListById(String tablename, int secim, String order) throws IOException {
    	
    	Scanner scanner = new Scanner(System.in);
    	Table xTable = new Table();
    	xTable.name = tablename;
    	String fileName = xTable.name ; 
    	int countOfDataById=-1;
    	xTable=getTable(fileName);
        
        int numInttype=xTable.counts.countInt;
	    int numChartype=xTable.counts.countChar;
	    int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype;
    	int columnCount=numInttype+numChartype;
    	try (RandomAccessFile raf = new RandomAccessFile(fileName+"Records.bin", "r")) {
    		
    		countOfDataById=(int)raf.length()/RECORD_SIZE;
    		
    	} catch(IOException e) {		
    		 e.printStackTrace();	
    	} 
    	
    	Object[][] data = new Object[countOfDataById][columnCount];
    	
    	try (RandomAccessFile raf = new RandomAccessFile(fileName+"Records.bin", "r")) {
    		raf.seek(0);
    		for(int i =0;i<countOfDataById;i++) {
    			for(int j=0;j<columnCount;j++) {
    				if(xTable.columns[j].type.equals("int")) {
    					int value=raf.readInt();
    					data[i][j]=value;		
    				}
    				else if(xTable.columns[j].type.equals("char")) {
    					byte[] veri = new byte[CHAR_SIZE]; 
                        int okunanBaytSayisi = raf.read(veri); 
                        String value =new String(veri, 0, okunanBaytSayisi, StandardCharsets.UTF_8);
                        data[i][j]=value.replaceAll("\\p{C}", "");	
    				}	
    			}   			
    		}    		
    	} catch(IOException e) {   		
   		 e.printStackTrace();   		
   	    }
    	
    	
    	int c = secim;
    	
    	if(order.equals("descending"))
    	{
    		sortArrayByColumnDescending(data,c);
    	}
    	else if(order.equals("ascending"))
    	{
    		sortArrayByColumnAscending(data,c);
    	}
    	
		return data;
    }
    
    public static void newTable(String recorddata[][], String nameTable) throws FileNotFoundException, IOException
    {
    	Scanner scanner = new Scanner(System.in);

    	// Get table information from user
    	Table table = new Table();
    	
    	table.name = nameTable;
    	table.num_columns = recorddata.length;
    	String fileName = table.name + "_metadata.bin"; // Metadata dosyasının adını değiştirdik

    	try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) { // Try-with-resources kullanımını öneririm
    	    // Kolon sayısını yaz
    	    raf.writeInt(table.num_columns);
    	    table.columns[0] = new Column();
    	    table.columns[0].name=recorddata[0][0];
    	    table.columns[0].type=recorddata[0][1];
    	    byte[] colNameBytes = table.columns[0].name.getBytes("UTF-8");
    	    byte[] colTypeBytes = table.columns[0].type.getBytes("UTF-8");
    	    raf.writeInt(colNameBytes.length); // Kolon adı boyutunu yaz
	        raf.write(colNameBytes); // Kolon adını yaz
	        raf.writeInt(colTypeBytes.length); // Kolon tipi boyutunu yaz
	        raf.write(colTypeBytes); // Kolon tipini yaz
    	    // Her bir kolon için adını ve tipini yaz
    	    for (int i = 1; i < table.num_columns; i++) {
    	    	 table.columns[i] = new Column();
	    	     table.columns[i].name =recorddata[i][0] ;
	    	     table.columns[i].type = recorddata[i][1] ;
    	               
    	        // Kolon adı ve tipini byte dizisine çevirerek yaz
    	        colNameBytes = table.columns[i].name.getBytes("UTF-8");
    	        colTypeBytes = table.columns[i].type.getBytes("UTF-8");
    	        raf.writeInt(colNameBytes.length); // Kolon adı boyutunu yaz
    	        raf.write(colNameBytes); // Kolon adını yaz
    	        raf.writeInt(colTypeBytes.length); // Kolon tipi boyutunu yaz
    	        raf.write(colTypeBytes); // Kolon tipini yaz
    	    }
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}

        // Create records file
        ObjectOutputStream records_file = new ObjectOutputStream(new FileOutputStream(table.name + "Records"+".bin"));
        records_file.close();

        // Create index file
        ObjectOutputStream index_file = new ObjectOutputStream(new FileOutputStream(table.name + "Index"+".bin"));
        index_file.close();
    }
    
    public static Table getTable(String fileName) {
    	ColumnCounts count=new ColumnCounts();
    	Table xTable = new Table();
    	xTable.name=fileName+"_metadata.bin";
    	fileName=xTable.name;
    	 int numChartype=0;
         int numInttype=0;
         int numColumns1=0;
         
    	 try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) { 
             
             int numColumns = raf.readInt();
             numColumns1=numColumns;
             xTable.columns=new Column[numColumns];

             for (int i = 0; i < numColumns; i++) {
                 Column column = new Column();

                 int colNameLength = raf.readInt();
                 byte[] colNameBytes = new byte[colNameLength];
                 raf.readFully(colNameBytes); 
                 column.name = new String(colNameBytes, "UTF-8"); 
                          
                 int colTypeLength = raf.readInt(); 
                 byte[] colTypeBytes = new byte[colTypeLength]; 
                 raf.readFully(colTypeBytes); 
                 column.type = new String(colTypeBytes, "UTF-8"); 
                 if(column.type.equals("int")) {
                 	numInttype++;
                 }
                 else if(column.type.equals("char")) {
                 	numChartype++;
                 }
                 xTable.columns[i]=column;
           
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 count.countChar=numChartype;
    	 count.countInt=numInttype;
    	 xTable.counts=count;
    	
    	return xTable;
    }
    public static boolean primarySearch_CHAR(String newValue,String fileName,int RECORD_SIZE) throws IOException {
    	int x = 0;
    	try (RandomAccessFile recordsFile = new RandomAccessFile(fileName + "Records.bin", "r")) {
    		
    		
    		recordsFile.seek(INT_SIZE);
    		x=(int)recordsFile.getFilePointer();
    		while (recordsFile.getFilePointer() < recordsFile.length()) {
    			
    			byte[] veri = new byte[newValue.length()]; 
                int okunanBaytSayisi = recordsFile.read(veri); 
                String currPrimary =new String(veri, 0, okunanBaytSayisi, StandardCharsets.UTF_8);
                if (currPrimary.equalsIgnoreCase(newValue)) { 
                   
                   
                   return false;
                }	    
                
               x += RECORD_SIZE+INT_SIZE;
               recordsFile.seek(x);
    		}
	  } catch (IOException e) {
          e.printStackTrace();
      }  
    	return true;
	}
    
    public static boolean primarySearch_INT(int newValue,String fileName,int RECORD_SIZE) throws IOException {
    	
    	try (RandomAccessFile recordsFile = new RandomAccessFile(fileName + "Records.bin", "r")) {
    		int currPrimary = -1;
    		int x = INT_SIZE;
    		while (recordsFile.getFilePointer() < recordsFile.length()) {	
                recordsFile.seek(x);
    			currPrimary = recordsFile.readInt();
                if (currPrimary == newValue) { 
                    return false;
                }	    
                x += RECORD_SIZE+INT_SIZE;
                recordsFile.seek(x);
    		}
        } catch (IOException e) {
          e.printStackTrace();
      	} 
    	return true;
    }
    public static void newData(String[]arr ,String tablename ) throws FileNotFoundException, IOException, ClassNotFoundException
    {
    	Scanner scanner = new Scanner(System.in);
        
        String fileName = tablename;
        String temp=fileName;
       
        Table table = new Table();
        table=getTable(fileName);
        int numChartype=table.counts.countChar;
        int numInttype=table.counts.countInt;
        
        int numColumns1=table.counts.countChar+table.counts.countInt;
        String recordsFileName = temp + "Records.bin"; 
        try (RandomAccessFile raf = new RandomAccessFile(recordsFileName, "rw")) { 
 
        	raf.seek(raf.length());
        	 RandomAccessFile indexFile = new RandomAccessFile(temp+"Index.bin", "rw");
             long lastId = -1;
             long lastOffset = -1;
    
             if(indexFile.length()==4) {
            	 lastId = 0;
             }
             
             else {
            	  try (RandomAccessFile caf = new RandomAccessFile(temp+"Index.bin", "r")) {
                 	 
    		          caf.seek(caf.length() - 16);
                      long id = caf.readLong(); 
                      long offset = caf.readLong();
                      
                      lastId = id; 
                      lastOffset = offset;

                  } catch (IOException e) {
                      e.printStackTrace();
                  }
     
             }
             int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype; 
        	
      
                if(lastId==0) {
                	raf.seek(0);
                }
             
                for (int j = 0; j < numColumns1; j++) {
                
                    if(table.columns[j].type.equals("int") && j == 0) {
                        int value = (int) lastId + 1;   
                        raf.writeInt(value);
                        RECORD_SIZE=RECORD_SIZE-INT_SIZE;
       
                    }
                    else if (table.columns[j].type.equals("int")) {
                        
                        String value = arr[j];
                        int a = Integer.parseInt(value);
                        raf.writeInt(a);
                        
                        RECORD_SIZE=RECORD_SIZE-INT_SIZE;
                       
                    } 
                    
                    else if (table.columns[j].type.equals("char")) {
  
                        String value = arr[j];
                        byte[] dataBytes = value.getBytes(StandardCharsets.UTF_8);
                        raf.write(dataBytes);
                        int x=CHAR_SIZE-dataBytes.length;
                
                        for (int z = 0; z < x; z++) {
                            raf.writeByte(0);
                        }   
                    } 
                   
                   
                }
                  
   
                indexFile.seek(indexFile.length());
                long id = lastId + 1;
                long offset =raf.getFilePointer();
                String line = id + "," + offset + System.lineSeparator();
                indexFile.writeLong(id);
              
                indexFile.writeLong(offset);                  
       
           indexFile.close();          
        } catch (IOException e) {
            e.printStackTrace();
        }    	
    }
    
    
    public static void updateDataById(int id , String tablename, String[] data) throws FileNotFoundException, IOException, ClassNotFoundException{
    	  	
        	Scanner scanner = new Scanner(System.in);
           
            int currId=-1;
            
            String fileName = tablename;
            Table xTable=getTable(fileName);
            
            int numInttype=xTable.counts.countInt;
    	    int numChartype=xTable.counts.countChar;
    	    
            int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype; 
                   
            try (RandomAccessFile recordsFile = new RandomAccessFile(fileName + "Records.bin", "rw")) {
                int search_id = id;    
                recordsFile.seek(0);
                boolean found=false;
                while (recordsFile.getFilePointer() < recordsFile.length()) {
                     currId = recordsFile.readInt();
                    if (currId == search_id) { 
                        
                        found=true;     
                        
                        for(int i=1;i<numInttype+numChartype;i++) {
                        	
                	    	if(xTable.columns[i].type.equals("int") ) {
                	    		
                	    		int value = recordsFile.readInt();
                                int newValue =  Integer.parseInt(data[i]);
                                recordsFile.seek(recordsFile.getFilePointer()-INT_SIZE);
                                recordsFile.writeInt(newValue);            
                            }
                	    	
                            else if (xTable.columns[i].type.equals("char")) {
                            	
                            	byte[] veri = new byte[CHAR_SIZE]; 
                                int okunanBaytSayisi = recordsFile.read(veri); 
                                String value =new String(veri, 0, okunanBaytSayisi, StandardCharsets.UTF_8);  
                                String newValue = data[i];
                                
                                recordsFile.seek(recordsFile.getFilePointer()-CHAR_SIZE);
                                byte[] dataBytes = newValue.getBytes(StandardCharsets.UTF_8);                                
                                recordsFile.write(dataBytes);
                                
                                if(newValue.length()<value.length()) {
                                	
                                	int x = value.length()-newValue.length();
                                	
                                	for(int h=0;h<x;h++) {
                                		recordsFile.write(0);
                                	}
                                }                                     
                            } 
                	    }
                        
                        break;
                    }
                    int x=RECORD_SIZE*currId;
                    recordsFile.seek(x);
                }
                
                if (!found) {
                    System.out.println("Record not found.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }  	
    }   
    
    public static SearchResult searchID(String fileName, int id) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	Index index = new Index();
    	boolean found = false;
    	
        try (RandomAccessFile indexFile = new RandomAccessFile(fileName + "Index.bin", "r")) {
            
            long search_id = (long)id;
           
            indexFile.seek(4);
            
            while (indexFile.getFilePointer() < indexFile.length()) {
            	
                long currId = indexFile.readLong(); 
                long offset = indexFile.readLong(); 

                if (currId == search_id) { 
                    index.id = currId;
                    index.offset = offset;
                    found = true;
                    break;
                }
            }
                        
            if (!found) {
                System.out.println("Record not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new SearchResult(index, found);
		
    }
    
    public static void updateDataByOffset(int id, String tablename, String data[]) throws FileNotFoundException, IOException, ClassNotFoundException{
    	
    	Scanner scanner = new Scanner(System.in);
     
        String fileName = tablename;
        
        Table xTable=getTable(fileName);
       
        SearchResult result = searchID(fileName,id);

        if (result.found) {           
            RandomAccessFile records_file1 = new RandomAccessFile(fileName + "Records.bin", "rw");
           
            Record records;
            int numInttype = xTable.counts.countInt;
            int numChartype = xTable.counts.countChar;

            int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype; 
         
            records_file1.seek(result.index.offset - RECORD_SIZE);
            int c = records_file1.readInt();
            
                      
            
            for (int i = 1; i < numInttype + numChartype; i++) {
            	
                if (xTable.columns[i].type.equals("int")) {
                	
                    int value = records_file1.readInt();
                    int newValue = Integer.parseInt(data[i]);  
                    records_file1.seek(records_file1.getFilePointer()-INT_SIZE);
                    records_file1.writeInt(newValue);
                    
                } 
                else if (xTable.columns[i].type.equals("char")) {
                    
                    byte[] veri = new byte[CHAR_SIZE]; 
                    int okunanBaytSayisi = records_file1.read(veri); 
                    String value =new String(veri, 0, okunanBaytSayisi, StandardCharsets.UTF_8);
                    String newValue = data[i];
                    records_file1.seek(records_file1.getFilePointer()-CHAR_SIZE);
                   
                    byte[] dataBytes = newValue.getBytes(StandardCharsets.UTF_8);
                    records_file1.write(dataBytes);
                    
                    if(newValue.length()<value.length()) {
                    	
                    	int x = value.length()-newValue.length();
                    	
                    	for(int h=0;h<x;h++) {
                    		records_file1.write(0);
                    	}
                    }           
                }
            }

            records_file1.close();
        } else {
            System.out.println("not found");
        }
    }
    
    public static String[] searchData(int id , String tablename) throws FileNotFoundException, IOException, ClassNotFoundException
    {
    	Scanner scanner = new Scanner(System.in);
    	
    	
        String fileName = tablename;
        
        Table xTable=getTable(fileName);
        String searcharr[] = new String[xTable.columns.length];
        SearchResult result = searchID(fileName,id);

        if (result.found) {
           
            RandomAccessFile records_file1 = new RandomAccessFile(fileName + "Records.bin", "r");

            Record records;
            int numInttype = xTable.counts.countInt;
            int numChartype = xTable.counts.countChar;

            int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype;

            records_file1.seek(result.index.offset - RECORD_SIZE);
            	
            for (int i = 0; i < numInttype + numChartype; i++) {
            	
                if (xTable.columns[i].type.equals("int")) {
                    int value = records_file1.readInt();
                    searcharr[i] = value+"";
                  
                } 
                else if (xTable.columns[i].type.equals("char")) {
                    
                    byte[] veri = new byte[CHAR_SIZE]; 
                    int okunanBaytSayisi = records_file1.read(veri); 
                    String value =new String(veri, 0, okunanBaytSayisi, StandardCharsets.UTF_8);
                    searcharr[i] = value.replaceAll("\\p{C}", "");
               
                }
            }
            records_file1.close();
        } else {
            System.out.println("not found");
        }
		return searcharr;
    }
    
public static String [] seqSearchById(int id, String tablename) throws IOException, ClassNotFoundException{
    	
    	Scanner scanner = new Scanner(System.in);
    	
        String fileName = tablename;
        
        Table xTable=getTable(fileName);
        String[] searcharr2 = new String [xTable.columns.length];
        int currId=-1;
        int numInttype=xTable.counts.countInt;
	    int numChartype=xTable.counts.countChar;
	    
        int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype;
          
        try (RandomAccessFile recordsFile = new RandomAccessFile(fileName + "Records.bin", "r")) {
        	boolean found=false;
        	
           
            int search_id = id;
            
            recordsFile.seek(0);
            
            while (recordsFile.getFilePointer() < recordsFile.length()) {
                 currId = recordsFile.readInt(); 
                 if (currId == search_id) { 
                    
                    found=true;
                    System.out.println(found);
                    
                    searcharr2[0] = search_id+""; 
                    for(int i=1;i<numInttype+numChartype;i++) {
                    	
            	    	if(xTable.columns[i].type.equals("int") ) {
                            int value = recordsFile.readInt();
                            searcharr2[i] = value+""; 
                                          
                        }
            	    	
                        else if (xTable.columns[i].type.equals("char")) {
                        	byte[] veri = new byte[CHAR_SIZE]; 
                            int okunanBaytSayisi = recordsFile.read(veri);
                            String value =new String(veri, 0, okunanBaytSayisi, StandardCharsets.UTF_8);
                            searcharr2[i] = value.replaceAll("\\p{C}", "");                                
                        } 
            	    }
                    break;
                }
                int x=RECORD_SIZE*currId;
                recordsFile.seek(x);
            }
            
            if (!found) {
                System.out.println("Record not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return searcharr2;
           	
    }
    
    public static String[][] readAll(String tablename) throws IOException, ClassNotFoundException {
    	
        Scanner scanner = new Scanner(System.in);
        
        String fileName = tablename;
        
        Table xTable=getTable(fileName);
        int numInttype=xTable.counts.countInt;
	    int numChartype=xTable.counts.countChar;
	    
	    int INT_SIZE = 4; // int tipindeki sütunların byte boyutu
        int CHAR_SIZE = 20; // char tipindeki sütunların byte boyutu
        int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype; // her bir kaydın toplam boyutu
        	
       
        int id = 0;
        String[][] data = null;
        
       
       
        try(RandomAccessFile recordsFile = new RandomAccessFile(fileName + "Records.bin", "r")) {
        	
        	if(  recordsFile.length()/ RECORD_SIZE>0){
        	 data =  new String[(int) (recordsFile.length()/RECORD_SIZE)][numChartype+numInttype];
        	}
        	recordsFile.seek(0);
        	int count= 0;
        	
        	
        	 if(recordsFile.length()/ RECORD_SIZE>0){
        		 
        	

	        while (recordsFile.getFilePointer() < recordsFile.length()) {	
	        	
	        	  
	        	id = recordsFile.readInt(); 
	        	
	        	data[count][0] = id+"";
	          
	        	
	        	for(int i=1;i<numInttype+numChartype;i++) 
	        	{
	        		
	        		if(xTable.columns[i].type.equals("int") ) 
	        		{
	    	    		int value = recordsFile.readInt(); 
	                    
	                    data[count][i] = value+"";
	                    
	                }
	        		
	                else if (xTable.columns[i].type.equals("char")) 
	                {
	                	byte[] veri = new byte[CHAR_SIZE]; 
                        int okunanBaytSayisi = recordsFile.read(veri); // verileri oku ve okunan bayt sayısını a
                        String value =new String(veri, 0, okunanBaytSayisi, StandardCharsets.UTF_8);
                        data[count][i] =value.replaceAll("\\p{C}", "");
                                           
	                }  
	        		
	        		
	    	    	 	    	
	            }
	        	count++;
	        	int x=id*RECORD_SIZE;
	            recordsFile.seek(x);   
	            
	        }
	        	recordsFile.close();
		    } 
        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
       
		return data;
    }
    
public static void deleteDataByOffset(int id,String tablename) throws FileNotFoundException, IOException, ClassNotFoundException{
    	
    	Scanner scanner = new Scanner(System.in);

       
        String fileName = tablename;
        Table xTable=getTable(fileName);
            
        int numInttype=xTable.counts.countInt;
	    int numChartype=xTable.counts.countChar;

        int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype; 
        
        SearchResult result = searchID(fileName,id);
        
        if(result.found){
        	
        	try (RandomAccessFile recordsFile = new RandomAccessFile(fileName + "Records.bin", "rw")) {
            	recordsFile.seek(result.index.offset - RECORD_SIZE);
            	long nextRecordOffset = result.index.offset + RECORD_SIZE;
            	if(nextRecordOffset>recordsFile.length()) {
            		recordsFile.setLength(recordsFile.length() - RECORD_SIZE);
            	}
            	else {
            		while (recordsFile.getFilePointer()  < recordsFile.length() - RECORD_SIZE) {
                        
                        recordsFile.readInt();
                        long currOffset=recordsFile.getFilePointer();
                        recordsFile.seek(nextRecordOffset- RECORD_SIZE);
                        recordsFile.readInt();
                        byte[] nextRecordData=new byte[RECORD_SIZE-INT_SIZE];
                        recordsFile.read(nextRecordData);
                        recordsFile.seek(currOffset);
                        recordsFile.write(nextRecordData);
                        nextRecordOffset=nextRecordOffset + RECORD_SIZE;
                       
                   }
                   recordsFile.setLength(recordsFile.length() - RECORD_SIZE);
            	}
               
                try (RandomAccessFile indexFile = new RandomAccessFile(fileName + "Index.bin", "rw")) {
                   
                    indexFile.setLength(indexFile.length() - 16);
                    System.out.println("Record has been deleted.");
                }
            	catch (IOException e) {
            		e.printStackTrace();
            	}
            }
            catch (IOException e) {
        		e.printStackTrace();
        	}	        	
        }        
    }

public static void deleteDataById(int id , String tablename) throws FileNotFoundException, IOException, ClassNotFoundException{
	
	Scanner scanner = new Scanner(System.in);
   
    String fileName = tablename;
    Table xTable=getTable(fileName);
    int currId=-1;
    int numInttype=xTable.counts.countInt;
    int numChartype=xTable.counts.countChar;
    
    int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype; 
    long startOffset=-1;

    try (RandomAccessFile recordsFile = new RandomAccessFile(fileName + "Records.bin", "r")) {
       
        int search_id = id; 
        recordsFile.seek(0);
        boolean found=false;
        
        while (recordsFile.getFilePointer() < recordsFile.length()) {
             currId = recordsFile.readInt(); 
            if (currId == search_id) { 
            	startOffset=recordsFile.getFilePointer()-INT_SIZE+RECORD_SIZE;
                found=true;    
                break;
            }
            
            int x=RECORD_SIZE*currId;
            recordsFile.seek(x);
        }
        
        if (!found) {
            System.out.println("Record not found.");
        }
        
        else {
        	try (RandomAccessFile recordsFileforDelete = new RandomAccessFile(fileName + "Records.bin", "rw")) {
        		recordsFileforDelete.seek(startOffset - RECORD_SIZE);
            	long nextRecordOffset = startOffset + RECORD_SIZE;
            	if(nextRecordOffset>recordsFile.length()) {
            		recordsFileforDelete.setLength(recordsFile.length() - RECORD_SIZE);
            	}
            	else {
            		while (recordsFileforDelete.getFilePointer()  < recordsFileforDelete.length() - RECORD_SIZE) {
                        
            			recordsFileforDelete.readInt();
                        long currOffset=recordsFileforDelete.getFilePointer();
                        recordsFileforDelete.seek(nextRecordOffset- RECORD_SIZE);
                        recordsFileforDelete.readInt();
                        byte[] nextRecordData=new byte[RECORD_SIZE-INT_SIZE];
                        recordsFileforDelete.read(nextRecordData);
                        recordsFileforDelete.seek(currOffset);
                        recordsFileforDelete.write(nextRecordData);
                        nextRecordOffset=nextRecordOffset + RECORD_SIZE;
                       
                   }
            		recordsFileforDelete.setLength(recordsFile.length() - RECORD_SIZE);
            	}
               
                try (RandomAccessFile indexFile = new RandomAccessFile(fileName + "Index.bin", "rw")) {
                   
                    indexFile.setLength(indexFile.length() - 16);
                   
                }
            	catch (IOException e) {
            		e.printStackTrace();
            	}
        		
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }
    } catch (IOException e) {
        e.printStackTrace();
    }	
}
public static String [] searchWordOrNuM(String colsName,String SearchText ,String tablename) throws FileNotFoundException, IOException, ClassNotFoundException
{
	
	
    String fileName = tablename;
    
    Table xTable=getTable(fileName);
    String[] searcharrWord = new String [xTable.columns.length];
    int currId=-1;
    int numInttype=xTable.counts.countInt;
    int numChartype=xTable.counts.countChar;
    
    int RECORD_SIZE = INT_SIZE * numInttype + CHAR_SIZE * numChartype;
    
    String colType = "";
    int search1 = 0;
    String search2 = "";
    int size = 4;   
      
    try (RandomAccessFile recordsFile = new RandomAccessFile(fileName + "Records.bin", "r")) {
    	       	
    	boolean found=false;
        
        String colName = colsName;
                    
        recordsFile.seek(4);
        
       
        
        for (int j = 1; j < numInttype + numChartype; j++) {
        	
        	
 
            if(xTable.columns[j].name.equalsIgnoreCase(colName)) {
               colType = xTable.columns[j].type;
               
               break;                                    
            }
            if(xTable.columns[j].type.equals("int") ) {
            	
            	size += INT_SIZE;
            	
            	
            }
            else {
            	size += CHAR_SIZE;
            	
            }
            
        }
        
        if(colType.equals("int") ) {
	    		
             search1 = Integer.parseInt(SearchText);
                          
        }
    	
        else if (colType.equals("char")) {
        	 
             search2 = SearchText;
             
        }
        
        int x=size;
        while (recordsFile.getFilePointer() < recordsFile.length()) {
        	recordsFile.seek(x);
        	if(colType.equals("int") ) {
        		int value = recordsFile.readInt();
        		if(value == search1) {
        			found=true;
        			
        			recordsFile.seek((int)recordsFile.getFilePointer()- (size+INT_SIZE));
	            	  for (int i = 0; i < numInttype + numChartype; i++) {
	                  	
	                      if (xTable.columns[i].type.equals("int")) {
	                          int value1 = recordsFile.readInt();
	                          searcharrWord[i] = value1+"";
	                         
	                      } 
	                      else if (xTable.columns[i].type.equals("char")) {
	                          
	                          byte[] veri = new byte[CHAR_SIZE]; 
	                          int okunanBaytSayisi = recordsFile.read(veri); 
	                          String value1 =new String(veri, 0, okunanBaytSayisi, StandardCharsets.UTF_8);
	                          searcharrWord[i] = value1.replaceAll("\\p{C}", "");
	                       
	                      }
	                  }
	            	  
	             }
        		
        		
        	}
        	 else if (colType.equals("char")) {
        		 byte[] veri = new byte[CHAR_SIZE]; 
	             int okunanBaytSayisi = recordsFile.read(veri);
	             String value =new String(veri, 0, search2.length(), StandardCharsets.UTF_8);
	             
	             
	             if(value.equalsIgnoreCase(search2)) {
	            	 found=true;
	            	 recordsFile.seek((int)recordsFile.getFilePointer()- (size+CHAR_SIZE));	
	            	
	            	 
	            	  for (int i = 0; i < numInttype + numChartype; i++) {
	                  	
	                      if (xTable.columns[i].type.equals("int")) {
	                          int value1 = recordsFile.readInt();
	                          searcharrWord[i]=value1+"";
	                         
	                      } 
	                      
	                      else if (xTable.columns[i].type.equals("char")) {
	                          
	                          byte[] veri1 = new byte[CHAR_SIZE]; 
	                          int okunanBaytSayisi1 = recordsFile.read(veri1); 
	                          String value1 =new String(veri1, 0, okunanBaytSayisi1, StandardCharsets.UTF_8);
	                          searcharrWord[i] = value1.replaceAll("\\p{C}", ""); 
	                         
	                      }
	                  }
	             }	
	             
        	 }
        	x+=RECORD_SIZE;
        	
        }
        if (!found) {
            System.out.println("Record not found.");
        }
        else
        {	
        	System.out.println("Bulundu.");
        }
        
        
        
    } catch (IOException e) {
        e.printStackTrace();
        
        System.out.println("Hata Bulundu.");
    }
    return searcharrWord;
    
}



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DATABASE");
        System.out.println("1.NEW TABLE");
        System.out.println("2.ADD RECORD");
        System.out.println("3.SEARCH A RECORD by offset");
        System.out.println("4.list  RECORDs");
        System.out.println("5.SEARCH A RECORD by id");
        System.out.println("6.UPDATE A RECORD by offset");
        System.out.println("7.UPDATE A RECORD by id");
        System.out.println("7.DELETE A RECORD by offset");
        System.out.println("7.DELETE A RECORD by id");
        String input = "";
        while(!input.equals("q")) 
        {
        	input = scanner.nextLine();
        	if(input.equals("1")) {
        		//newTable();
        	}
        	if(input.equals("2")) {
        		//newData();
        	}
        	if(input.equals("3")) {
        		//searchData();
        	}
        	if(input.equals("4")) {
        		//readAll();
        	}      	
        	if(input.equals("5")) {
        		//seqSearchById();
            }
        	if(input.equals("6")) {
        		//updateDataByOffset(1);
            }
        	if(input.equals("7")) {
        		//updateDataById();
        	}
        	if(input.equals("8")) {
        		//deleteDataByOffset();
        	}
        	if(input.equals("9")) {
        		//deleteDataById();
        	}
        	if(input.equals("10")) {
        		//ascendingOrderListByIndex();
        	}
        	if(input.equals("11")) {
        		//ascendingOrderListById();
        	}
        }
    }
}