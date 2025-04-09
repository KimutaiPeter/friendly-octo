package friendly_octo;


import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;



public final class App {
    private App() {
    }

    static String[] module = {"Lecturer Module", "Student Module", "Exit"};
    static String[] lecturer_module = {"Enter Marks", "View Report","Back", "Exit"};
    static String[] student_module = {"View my report","Back", "Exit"};

    
    public static void main (String[] args) throws Exception {
        Terminal terminal = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

        int selected_activity=0;
        int selected_user=0;
        int selected_option=0;
        
        while (true) {
            terminal.puts(InfoCmp.Capability.clear_screen);
            terminal.flush();

            if(selected_user==0){
                for (int i = 0; i < module.length; i++) {
                    if (i == selected_option) {
                        System.out.println("> \u001B[32m" + module[i] + "\u001B[0m"); // Green highlight
                    } else {
                        System.out.println("  " + module[i]);
                    }
                }
            }else if(selected_user==1){
                for (int i = 0; i < module.length; i++) {
                    if (i == selected_option) {
                        System.out.println("> \u001B[32m" + lecturer_module[i] + "\u001B[0m"); // Green highlight
                    } else {
                        System.out.println("  " + lecturer_module[i]);
                    }
                }
            }else if(selected_user==2){
                for (int i = 0; i < module.length; i++) {
                    if (i == selected_option) {
                        System.out.println("> \u001B[32m" + student_module[i] + "\u001B[0m"); // Green highlight
                    } else {
                        System.out.println("  " + student_module[i]);
                    }
                }
            }


            int ch = terminal.reader().read();
            System.out.println(ch);
            // Arrow key sequences start with ESC (27)
            if (ch == 27) {
                int next1 = terminal.reader().read();
                int next2 = terminal.reader().read();
                System.out.println(next1+"Arrow keys");
                System.out.println(next2+"Arrow keys");

                if (next1 == 79) { // '['
                    if (next2 == 65) { // 'A' (Up)
                        if(selected_user==0){
                            selected_option = (selected_option - 1 + module.length) % module.length;
                        }else if(selected_user==1 ){
                            selected_option = (selected_option - 1 + lecturer_module.length) % lecturer_module.length;
                        }else if(selected_user==2 ){
                            selected_option = (selected_option - 1 + student_module.length) % student_module.length;
                        }
                        
                    } else if (next2 == 66) { // 'B' (Down)
                        
                        if(selected_user==0 ){
                            
                            selected_option = (selected_option + 1) % module.length;
                        }else if(selected_user==1 ){
                            selected_option = (selected_option + 1) % lecturer_module.length;
                        }else if(selected_user==2 ){
                            selected_option = (selected_option + 1) % lecturer_module.length;
                        }
                    }
                }
            } else if (ch == 10 || ch == 13) { // Enter
                
                if(selected_user==0){
                    if (module[selected_option].equals("Exit")) break;
                    selected_user=selected_option+1;
                    
                }else if(selected_user==1 ){
                    if (lecturer_module[selected_option].equals("Exit")) break;
                    if (lecturer_module[selected_option].equals("Back")) selected_user=0;selected_activity=0;
                    selected_activity=selected_option+1;
                    
                }else if(selected_user==2 ){
                    if (student_module[selected_option].equals("Exit")) break;
                    if (student_module[selected_option].equals("Back")) selected_user=0;selected_activity=0;
                    selected_activity=selected_option+1;
                }

                //System.out.println("Selected something");
                

                //Do the activity
                if(selected_user==1){
                    if(selected_activity>0){
                        System.out.println("You selected: "+lecturer_module[selected_option] );
                        
                        if(selected_activity==2){
                            Utility functions=new Utility();
                            functions.view_all_reports();
                        }
                        if(selected_activity==1){
                            Utility functions=new Utility();
                            functions.enter_marks();
                        }
                        
                    }
                    
                }else if(selected_user==2){
                    if(selected_activity>0){
                        System.out.println("You selected: "+student_module[selected_option] );
                        if(selected_activity==1){
                            Utility functions=new Utility();
                            functions.view_my_report();
                        }

                    }
                }


                Thread.sleep(1000); // Pause before redrawing



            }
        }

        terminal.close();
    }
}
