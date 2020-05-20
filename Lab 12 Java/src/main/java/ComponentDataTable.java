import sun.security.krb5.internal.crypto.Des;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.*;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ComponentDataTable<T> extends JTable
{
    JFrame jFrame;
    JButton modifyComponent;

    public ComponentDataTable(final T jComponent) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        jFrame = new JFrame();

        jFrame.setLayout(new BorderLayout());

        Object[][] matrix;
        Object[] column;

        int i = 0;

        BeanInfo beanInfo = Introspector.getBeanInfo(jComponent.getClass());

        BeanDescriptor bd = beanInfo.getBeanDescriptor();

        Object[][] auxMatrix = new Object[1][beanInfo.getPropertyDescriptors().length];
        Object[] auxColumn = new Object[beanInfo.getPropertyDescriptors().length];

        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors())
        {
            final Method getter = pd.getReadMethod();

            if (getter != null)
            {
                final String attrName = pd.getName();
                final Object attrValue = getter.invoke(jComponent);

                if (attrValue != null && ((attrValue.getClass().equals(Integer.class) && (int)attrValue >= 0) || attrValue.getClass().equals(String.class)))
                {
                    if (attrName.equals("text") || attrName.equals("x") || attrName.equals("y") || attrName.equals("width") || attrName.equals("height") || attrName.equals("UI"))
                    {
                        auxColumn[i] = attrName;
                        auxMatrix[0][i] = attrValue;
                        i++;
                    }
                }
            }
        }

        matrix = new Object[1][i];
        column = new Object[i];

        int j = 0;
        while (auxColumn[j] != null && auxMatrix[0][j] != null)
        {
            matrix[0][j] = auxMatrix[0][j];
            column[j] = auxColumn[j];
            j++;
        }

        final JTable componentTable = new JTable(matrix, column);
        componentTable.setBounds(30,40,200,100);

        JScrollPane sp = new JScrollPane(componentTable);

        modifyComponent = new JButton("Modify Component");
        modifyComponent.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("Te fiut");
                if (jComponent.getClass().equals(JButton.class))
                {
                    JButton auxButton = (JButton) jComponent;

                    for (int i = 0; i < DesignPanel.buttonList.size(); i++)
                    {
                        if (auxButton.getText().equals(DesignPanel.buttonList.get(i).getText()))
                        {
                            int height = Integer.parseInt(componentTable.getValueAt(0, 0).toString());
                            String name = (String) componentTable.getValueAt(0, 1);
                            int width = Integer.parseInt(componentTable.getValueAt(0, 2).toString());
                            int x = Integer.parseInt(componentTable.getValueAt(0, 3).toString());
                            int y = Integer.parseInt(componentTable.getValueAt(0, 4).toString());

                            DesignPanel.buttonList.get(i).setText(name);
                            DesignPanel.buttonList.get(i).setName(name);
                            DesignPanel.buttonList.get(i).setBounds(x, y, width, height);
                        }
                    }
                }
                jFrame.dispose();
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

        modifyComponent.setBounds(50, 200, 50, 50);
        modifyComponent.setPreferredSize(new Dimension(50, 50));

        jFrame.setSize(900,300);
        jFrame.add(sp, BorderLayout.NORTH);
        jFrame.add(modifyComponent, BorderLayout.CENTER);
        jFrame.setVisible(true);
    }
}
