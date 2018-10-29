/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Onkar
 */
import java.io.*;
import java.io.File;
import java.util.Scanner;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
public class investment {
    
    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";   
        return resourcesPath;
    }
    
    
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        String abspath=new File(".").getAbsolutePath();
        String path=getResourcesPath();
        System.out.println("THis is"+path);
        MagicBooleans.trace_mode=false;
        Bot bot=new Bot("super",path);
        bot.brain.nodeStats();
        Chat cs=new Chat(bot);
        //bot.writeAIMLFiles();
         String line="";
         String response="";
         while(true)
         {
             System.out.print("Human : ");
             System.out.println(cs.multisentenceRespond(s.nextLine()));
             
         }
    }
}

