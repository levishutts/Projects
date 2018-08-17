import java.awt.EventQueue;

public class main {
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame1 frame = new frame1();
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
        
        record record = new record();
        play play = new play(); 
   //     soundManager soundManager = new soundManager();
        
    }
}
