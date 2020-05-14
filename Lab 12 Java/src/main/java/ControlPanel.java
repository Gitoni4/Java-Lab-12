import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener
{
    private JLabel componentLabel;
    private JComboBox componentList;

    private JLabel componentTextLabel;
    private JTextField componentTextField;

    private JButton buttonAdd;

    private ButtonListener buttonAction;

    public ControlPanel()
    {
        setLayout(new GridLayout(1, 5));

        componentLabel = new JLabel("Swing component : ");
        componentList = new JComboBox(new String[] { "Button", "Label" });

        componentTextLabel = new JLabel("Component name : ");
        componentTextField = new JTextField();
        componentTextField.setPreferredSize(new Dimension(50, 20));

        buttonAdd = new JButton("Add");

        Border innerBorder = BorderFactory.createTitledBorder("Control Panel");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

        buttonAdd.addActionListener(this);

        add(componentLabel);
        add(componentList);

        add(componentTextLabel);
        add(componentTextField);

        add(buttonAdd);
    }

    public String getComponentName()
    {
        return componentTextField.getText();
    }

    public String getComponentType()
    {
        return componentList.getSelectedItem().toString();
    }


    public void setButtonListener(ButtonListener b)
    {
        this.buttonAction = b;
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton clicked = (JButton)e.getSource();

        if (clicked == buttonAdd)
        {
            if (buttonAction != null)
            {
                buttonAction.addAction();
            }
        }
    }
}
