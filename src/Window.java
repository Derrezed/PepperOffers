import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {

    private JButton getAnswer = new JButton("Get results");
    private OfferList pepperek = new OfferList("https://www.pepper.pl/");
    private JTable table;
    private String[] columnNames = {"Name", "Temperature"};

    public Window() {
        super("PepperOffers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,800);
        setLocation(50,50);
        setVisible(true);
        setLayout(new GridLayout(2,1));
        add(getAnswer);
        getAnswer.addActionListener(this::actionPerformed);
        DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
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

    public String[][] getData() {
        ArrayList<Offer> offerList =  pepperek.getList();
        for(Offer offer : offerList) {
            data = [{offer.getOfferName(), offer.getOfferTemp()}];

        }
    }
}
