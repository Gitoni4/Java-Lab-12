import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame
{
    private ControlPanel controlPanel;

    private DesignPanel designPanel;

    public MainFrame()
    {
        super("Hello World");

        setLayout(new BorderLayout());

        controlPanel = new ControlPanel();

        designPanel = new DesignPanel();


        controlPanel.setButtonListener(new ButtonListener()
        {
            @Override
            public void addAction()
            {
                designPanel.addComponent(controlPanel.getComponentName(), controlPanel.getComponentType(), designPanel);
            }

            @Override
            public void componentDataTable() {

            }
        });



        add(controlPanel, BorderLayout.NORTH);

        add(designPanel, BorderLayout.CENTER);


        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
