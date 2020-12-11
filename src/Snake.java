import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Snake extends JFrame {

    public Snake() {
        add(new Board());
        setResizable(false);
        pack();

        setTitle("SNAKE???");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        EventQueue.invokeAndWait(new Runnable(){

			@Override
			public void run() {

                JFrame ex = new Snake();
                ex.setVisible(true);
			}           
        });
    }
    
}
