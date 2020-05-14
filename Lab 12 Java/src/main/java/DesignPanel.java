import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DesignPanel extends JPanel
{
    private ArrayList<JButton> buttonList;

    private ButtonListener buttonAction;

    public DesignPanel()
    {
        setLayout(null);

        Border innerBorder = BorderFactory.createTitledBorder("Design Panel");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

        buttonList = new ArrayList<>();
    }

    public void addComponent(String name, String type, DesignPanel designPanel)
    {
        if (!type.equals("Button"))
        {
            System.out.println("This type of component doesn't exist");
        }
        else
        {
            final JButton newButton = new JButton(name);
            newButton.setBounds(50, 50, (int) newButton.getPreferredSize().getWidth(), (int) newButton.getPreferredSize().getHeight());
            buttonList.add(newButton);

            final int[] screenX = new int[1];
            final int[] screenY = new int[1];
            final int[] myX = new int[1];
            final int[] myY = new int[1];

            newButton.addMouseMotionListener(new MouseMotionListener()
            {
                @Override
                public void mouseDragged(MouseEvent e)
                {
                    int deltaX = e.getXOnScreen();
                    int deltaY = e.getYOnScreen();

                    myX[0] = getX();
                    myY[0] = getY();

                    System.out.println(deltaX);
                    System.out.println(deltaY);

                    newButton.setBounds(deltaX - myX[0], deltaY - myY[0], (int) newButton.getPreferredSize().getWidth(), (int) newButton.getPreferredSize().getHeight());
                }

                @Override
                public void mouseMoved(MouseEvent e) { }
            });
            designPanel.add(newButton);
        }
    }


    public int checkButtonClick(JButton click)
    {
        for (int i = 0; i < buttonList.size(); i ++)
        {
            if (buttonList.get(i) == click)
            {
                return i;
            }
        }
        return -1;
    }
}
