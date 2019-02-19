import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    private JButton getAnswer = new JButton("Get results");
    private OfferList pepperek = new OfferList("https://www.pepper.pl/");


    public Window() {
        super("PepperOffers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,800);
        setLocation(50,50);
        setLayout(new GridLayout(pepperek.listSize(),6));
        setVisible(true);
        add(getAnswer);
        getAnswer.addActionListener(this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == getAnswer) {
            pepperek.showList();
        }
    }
}
