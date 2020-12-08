import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Graph extends JPanel implements ActionListener,
        MouseListener, MouseMotionListener, KeyListener {
    /*Costantes*/
    int value = 5000;
    int PlanoX = value, PlanoY = value;
    int Tamaño = 25;
    int Ancho = PlanoX / Tamaño, Largo = PlanoY / Tamaño;
    boolean Comienzo = true;
    int[][] life = new int[Ancho][Largo];
    int[][] beforelife = new int[Ancho][Largo];
    Timer Tiempo;

    public Graph(){
        setLayout(null);
        setPreferredSize(new Dimension(value, value));
        setBackground(new Color(23,32,42));
        Tiempo = new Timer(100, this);
        Tiempo.start();
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(27,38,49));
        Grid(g);
        //Respaw();
        Display(g);
    }

    public void Grid(Graphics t){
        for (int i = 0; i < (PlanoX / Tamaño); i++){
            t.drawLine(0,i * Tamaño,1 * PlanoX,i * Tamaño);
            t.drawLine(i * Tamaño,0,i * Tamaño, 1 * PlanoY);
        }
    }

    public void Respaw(){
        if (Comienzo){
            for (int x = 0; x < Ancho; x++){
                for (int y = 0; y < Largo; y++){ if ((int)(Math.random() * 5) == 0){ beforelife[x][y] = 1; } }
            }
        }
        Comienzo = false;
    }

    public void Display(Graphics o){
        Copy();
        o.setColor(new Color(11,83,69));
        for (int x = 0; x < Ancho; x++){
            for (int y = 0; y < Largo; y++){
                if (beforelife[x][y] == 1){
                    o.fillRect((x * Tamaño) + 1, (y * Tamaño) + 1, Tamaño - 1, Tamaño - 1);
                }
            }
        }
    }

    public void Limpiar(){
        for (int x = 0; x < Ancho; x++){
            for (int y = 0; y < Largo; y++){ beforelife[x][y] = 0; }
        }
        Comienzo = true;
    }

    public void Copy(){
        for (int x = 0; x < Ancho; x++){
            for (int y = 0; y < Largo; y++){ life[x][y] = beforelife[x][y]; }
        }
    }

    private int Check(int x, int y){
        int isLife = 0;

        isLife += life[(x + Ancho - 1) % Ancho][(y + Largo - 1) % Largo];
        isLife += life[(x + Ancho) % Ancho][(y + Largo - 1) % Largo];

        isLife += life[(x + Ancho + 1) % Ancho][(y + Largo - 1) % Largo];
        isLife += life[(x + Ancho - 1) % Ancho][(y + Largo) % Largo];

        isLife += life[(x + Ancho + 1) % Ancho][(y + Largo) % Largo];
        isLife += life[(x + Ancho - 1) % Ancho][(y + Largo + 1) % Largo];

        isLife += life[(x + Ancho) % Ancho][(y + Largo + 1) % Largo];
        isLife += life[(x + Ancho + 1) % Ancho][(y + Largo + 1) % Largo];

        return isLife;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int active;
        for (int x = 0; x < Ancho; x++){
            for (int y = 0; y < Largo; y++){
                active = Check(x,y);
                if (active == 3){
                    beforelife[x][y] = 1;
                }else if (active == 2 && life[x][y] == 1){
                    beforelife[x][y] = 1;
                }else{
                    beforelife[x][y] = 0;
                }
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int Code = e.getKeyCode();
        if (Code == KeyEvent.VK_R){ Respaw(); }
        if (Code == KeyEvent.VK_SPACE){ Tiempo.stop(); }
        if (Code == KeyEvent.VK_C){ Limpiar(); }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int Code = e.getKeyCode();
        if (Code == KeyEvent.VK_SPACE){ Tiempo.start(); }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tiempo.stop();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Tiempo.start();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX()/Tamaño;
        int y = e.getY()/Tamaño;

        if (life[x][y] == 0){ beforelife[x][y] = 1; }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
