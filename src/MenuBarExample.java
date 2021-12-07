import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBarExample extends JFrame implements ActionListener{

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu exitMenu;
    JMenu helpMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem editItem;
    JMenuItem runItem;
    ImageIcon loadIcon;
    ImageIcon saveIcon;
    ImageIcon editIcon;
    ImageIcon runIcon;


    MenuBarExample(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(new FlowLayout());
        this.setTitle("Directed Weighted Graph");
        this.getContentPane().setBackground(Color.PINK);//CHANGE COLO BACKGROUND

        loadIcon = new ImageIcon("./resources/load.jpg");
        saveIcon = new ImageIcon("./resources/save.png");
        editIcon = new ImageIcon("./resources/edit.jpg");
        runIcon = new ImageIcon("./resources/run.jpg");

        loadIcon = scaleImageIcon(loadIcon,20,20);
        saveIcon = scaleImageIcon(saveIcon,20,20);
        editIcon = scaleImageIcon(editIcon,20,20);
        runIcon = scaleImageIcon(runIcon,20,20);
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        exitMenu = new JMenu("Exit");
        helpMenu = new JMenu("Help");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        editItem = new JMenuItem("Edit");
        runItem = new JMenuItem("run");


        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        editItem.addActionListener(this);
        runItem.addActionListener(this);

        loadItem.setIcon(loadIcon);
        saveItem.setIcon(saveIcon);
        editItem.setIcon(editIcon);
        runItem.setIcon(runIcon);

        fileMenu.setMnemonic(KeyEvent.VK_F); // Alt + f for file
        exitMenu.setMnemonic(KeyEvent.VK_E); // Alt + e for edit
        helpMenu.setMnemonic(KeyEvent.VK_H); // Alt + h for help
        loadItem.setMnemonic(KeyEvent.VK_L); // l for load
        saveItem.setMnemonic(KeyEvent.VK_S); // s for save
        editItem.setMnemonic(KeyEvent.VK_D); // d for edit
        runItem.setMnemonic(KeyEvent.VK_R);

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(editItem);
        fileMenu.add(runItem);

        menuBar.add(fileMenu);
        menuBar.add(exitMenu);
        menuBar.add(helpMenu);


        this.setJMenuBar(menuBar);

        this.setVisible(true);
    }

    public static ImageIcon scaleImageIcon(ImageIcon imageIcon,int width,int height){
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);  // transform it back
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==loadItem) {
            System.out.println("beep boop you loaded a file");

        }
        if(e.getSource()==saveItem) {
            System.out.println("beep boop you saved a file");
        }
        if(e.getSource()== editItem) {
            System.out.println("you edit this item");
        }
        if (e.getSource()==runItem){
            System.out.println("run");
        }
        if (e.getSource()==exitMenu){
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        new MenuBarExample();
    }
}