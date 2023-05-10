import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimplePaint extends JFrame {
   private JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn;
   private DrawArea drawArea;
   private ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == clearBtn) {
            drawArea.clear();
         } else if (e.getSource() == blackBtn) {
            drawArea.black();
         } else if (e.getSource() == blueBtn) {
            drawArea.blue();
         } else if (e.getSource() == greenBtn) {
            drawArea.green();
         } else if (e.getSource() == redBtn) {
            drawArea.red();
         }
      }
   };

   public static void main(String[] args) {
      new SimplePaint().show();
   }

   public SimplePaint() {
      Container content = getContentPane();
      content.setLayout(new BorderLayout());
      drawArea = new DrawArea();
      content.add(drawArea, BorderLayout.CENTER);

      JPanel controls = new JPanel();
      clearBtn = new JButton("Clear");
      clearBtn.addActionListener(actionListener);
      blackBtn = new JButton("Black");
      blackBtn.addActionListener(actionListener);
      blueBtn = new JButton("Blue");
      blueBtn.addActionListener(actionListener);
      greenBtn = new JButton("Green");
      greenBtn.addActionListener(actionListener);
      redBtn = new JButton("Red");
      redBtn.addActionListener(actionListener);
      controls.add(clearBtn);
      controls.add(blackBtn);
      controls.add(blueBtn);
      controls.add(greenBtn);
      controls.add(redBtn);
      content.add(controls, BorderLayout.NORTH);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(600, 600);
      setVisible(true);
   }

   private class DrawArea extends JComponent {
      private Image image;
      private Graphics2D g2;
      private int x, y, oldX, oldY;

      public DrawArea() {
         setDoubleBuffered(false);
         addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
               oldX = e.getX();
               oldY = e.getY();
            }
         });

         addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
               x = e.getX();
               y = e.getY();

               if (g2 != null) {
                  g2.drawLine(oldX, oldY, x, y);
                  repaint();
                  oldX = x;
                  oldY = y;
               }
            }
         });
      }

      protected void paintComponent(Graphics g) {
         if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
         }

         g.drawImage(image, 0, 0, null);
      }

      public void clear() {
         g2.setPaint(Color.white);
         g2.fillRect(0, 0, getSize().width, getSize().height);
         g2.setPaint(Color.black);
         repaint();
      }

      public void black() {
         g2.setPaint(Color.black);
      }

      public void blue() {
         g2.setPaint(Color.blue);
      }

      public void green() {
         g2.setPaint(Color.green);
      }

      public void red() {
         g2.setPaint(Color.red);
      }
   }
}
