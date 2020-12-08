import javax.swing.*;

public class Main extends JFrame {
    public JScrollPane Bar;

    public Main(){
        /*Programando las propiedades de la ventana*/
        setTitle("Juego de la vida - John Conway");
        setSize(1300,600);
        setLocationRelativeTo(null);
        setResizable(true);
        Bar = new JScrollPane(new Graph());
        Bar.setSize(1300, 600);
        add(Bar);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Main ventana = new Main();
        ventana.setVisible(true);
    }
}
