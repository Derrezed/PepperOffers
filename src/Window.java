import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {

    private JButton getAnswer = new JButton("Get results");
    private OfferList pepperek = new OfferList("https://www.pepper.pl/");
    private JTable table;


    public Window() {
        super("PepperOffers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,800);
        setLocation(50,50);
        setVisible(true);
        setLayout(new GridLayout(2,1));
        add(getAnswer);
        getAnswer.addActionListener(this::actionPerformed);
        table = new JTable(pepperek.listSize(),1);
        add(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == getAnswer) {
            pepperek.showList();
            ArrayList<Offer> offerList =  pepperek.getList();
            int row = 0;
            for(Offer offer : offerList) {
                table.setValueAt(offer.getOfferName(), row, 0);
                row++;
            }
        }
    }
}
