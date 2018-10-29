/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Onkar
 */
import java.io.File;
import org.alicebot.ab.*;
import java.util.*;


public class ChatBot {
    private static final boolean  TRACE_MODE=false;
    static String botname="Sheldon";
    
    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        
        System.out.println(path);
        
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        
        return resourcesPath;
    }
    public static void main(String[] args)
    {
        try{
            
            Scanner s=new Scanner(System.in);
            String resourcesPath = getResourcesPath();
            System.out.println("This is:"+
                    resourcesPath);
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot bot = new Bot("super", resourcesPath);
            Chat chatSession = new Chat(bot);
            bot.brain.nodeStats();
            String textLine = "";
            while(true) {
                System.out.print("Human : ");
                textLine = s.nextLine();
                if ((textLine == null) || (textLine.length() < 1))
                    textLine = MagicStrings.null_input;
                if (textLine.equals("q")) {
                    System.exit(0);
                } else if (textLine.equals("wq")) {
                    bot.writeQuit();
                    System.exit(0);
                } else {
                    String request = textLine;
                    if (MagicBooleans.trace_mode)
                        System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                    String response = chatSession.multisentenceRespond(request);
                    while (response.contains("&lt;"))
                        response = response.replace("&lt;", "<");
                    while (response.contains("&gt;"))
                        response = response.replace("&gt;", ">");
                    System.out.println("Robot : " + response);
        }
            }
        }
        catch(Exception e){}
    }
}
