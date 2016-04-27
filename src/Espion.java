import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
class Espion implements MouseListener,KeyListener 
{
	private Frame frame; 
	private Robot robot; 
	private RemoteRobot obj;
	public Espion(Frame f) 
	{
		super(); 
		frame = f;
		// Robot pour simuler le click. 
		try {
			robot = new Robot(); 
			} catch (java.awt.AWTException e) { };
		int j;

		for (j=0;j<f.getComponentCount();j++) 
		{
			f.getComponent(j).addMouseListener(this);
			f.getComponent(j).addKeyListener(this);
		};
		
		try 
        { 
        	System.setSecurityManager(new RMISecurityManager());
        	obj = (RemoteRobot) Naming.lookup( "rmi://localhost/HelloServer");         //objectname in registry 
        } 
        catch (Exception e) 
        { 
           System.out.println("HelloClient exception: " + e.getMessage()); 
           e.printStackTrace(); 
        } 
	};

public void mouseClicked(MouseEvent e) { } public void mouseEntered(MouseEvent e) { } public void mouseExited(MouseEvent e) { } public void mousePressed(MouseEvent e) {

System.out.println("Press!");

} public void mouseReleased(MouseEvent e) {

System.out.println("Release!");

}

public void keyPressed(KeyEvent e) { } public void keyReleased(KeyEvent e) { // Simulation d’un click de souris.

	try {
		obj.pressAkey();
	} catch (RemoteException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
} public void keyTyped(KeyEvent e) { }

};