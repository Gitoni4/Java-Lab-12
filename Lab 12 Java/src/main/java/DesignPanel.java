import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DesignPanel extends JPanel
{
    public static ArrayList<JButton> buttonList;

    public static ArrayList<JLabel> labelList;

    private ButtonListener buttonAction;

    public DesignPanel()
    {
        setLayout(null);

        Border innerBorder = BorderFactory.createTitledBorder("Design Panel");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

        buttonList = new ArrayList<>();
        labelList = new ArrayList<>();
    }

    public void addComponent(String name, String type, DesignPanel designPanel)
    {
        if (!type.equals("Button") && !type.equals("Label"))
        {
            System.out.println("This type of component doesn't exist");
        }
        else if (type.equals("Button"))
        {
            final JButton newButton = new JButton(name);
            newButton.setBounds(50, 50, (int) newButton.getPreferredSize().getWidth(), (int) newButton.getPreferredSize().getHeight());
            buttonList.add(newButton);

            final int[] screenX = new int[1];
            final int[] screenY = new int[1];
            final int[] myX = new int[1];
            final int[] myY = new int[1];

            newButton.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    System.out.println(newButton.getX());
                    try {
                        new ComponentDataTable(newButton);
                    } catch (IntrospectionException | InvocationTargetException | IllegalAccessException introspectionException) {
                        introspectionException.printStackTrace();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

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
        else
        {
            final JLabel newLabel = new JLabel(name);
            newLabel.setBounds(50, 50, (int) newLabel.getPreferredSize().getWidth(), (int) newLabel.getPreferredSize().getHeight());
            labelList.add(newLabel);

            final int[] screenX = new int[1];
            final int[] screenY = new int[1];
            final int[] myX = new int[1];
            final int[] myY = new int[1];

            newLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(newLabel.getX());
                    try {
                        new ComponentDataTable(newLabel);
                    } catch (IntrospectionException | InvocationTargetException | IllegalAccessException introspectionException) {
                        introspectionException.printStackTrace();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            newLabel.addMouseMotionListener(new MouseMotionListener()
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

                    newLabel.setBounds(deltaX - myX[0], deltaY - myY[0], (int) newLabel.getPreferredSize().getWidth(), (int) newLabel.getPreferredSize().getHeight());
                }

                @Override
                public void mouseMoved(MouseEvent e) { }
            });
            designPanel.add(newLabel);
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
