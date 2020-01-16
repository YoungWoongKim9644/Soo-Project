
import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManagement {
	
	public static void FLineRW(ArrayList<ConsumerInfo> arraylist, int object_num) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("C:\\re-Sample.txt"));
		BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Sample.txt"));
		int cnt = object_num;
		String tmp = FileManagement.ObjtoStr(arraylist.get(object_num));
		while(cnt>=0)
		{
			out.write(in.readLine());
			out.newLine();
			cnt--;
		}
		out.write(tmp);	out.newLine();
		for(cnt = object_num+1; cnt < arraylist.size();cnt++)
		{
			out.write(FileManagement.ObjtoStr(arraylist.get(cnt)));
		}
		in.close();
		out.close();
	}
	
	public static String ObjtoStr(ConsumerInfo c)
	{
		String obj_str = "";
		obj_str = obj_str.concat(c.getName()).concat("/").concat(String.valueOf(c.getPoint())).concat("/").concat(String.valueOf(c.getSex())).concat("/").concat(String.valueOf(c.getNumber()));
		return obj_str;
	}
}
