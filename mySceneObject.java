package IMT2018018;
import animation.*;
import java.util.ArrayList;
public class mySceneObject extends SceneObject
{
 	private static int nameval=0;
 	private static int direction=0;
 	private String name;
 	private Point position;
 	private Point dest;
 
 	public mySceneObject()
 	{
  	nameval++;
  	name="."+(nameval);
  	position=new Point(0,0);
  	dest=new Point(0,0);
 	}
 	public String getObjName()
{
 	return name;
}

public Point getPosition()
{
	return position;
}
 
public void setPosition(int x, int y)
{
	position.setPos(x,y);
}

public void setDestPosition(int x, int y)
{
  	dest.setPos(x,y);
}
public BBox getBBox()
{
  	return new myBBox(new Point(position),new Point(position.getX()+15,position.getY()+10));
}
public ArrayList<Point> getOutline()
{
  	ArrayList<Point> a=new ArrayList<>(4);
  	a.add(new Point(position));
  	a.add(new Point(position.getX()+15,position.getY()));
  	a.add(new Point(position.getX()+15,position.getY()+10));
  	a.add(new Point(position.getX(),position.getY()+10));
  	return a;
}
public synchronized void updatePos(int deltaT) 
{

 	if(Math.abs(dest.getX()-position.getX())<Math.abs(35) && Math.abs(dest.getY()-position.getY())<Math.abs(35))
   		Scene.getScene().getActors().remove(this);
 	if (position.getX()<=5 && position.getY()<=5)
   		Scene.getScene().getActors().remove(this);  
  	Scene s;
  	s=Scene.getScene();
  	float dx,dy;
  	if(dest.getX()==position.getX())
  	{
   	dx=0;
   	dy=20;
   	if(dest.getY()<position.getY())
    		dy*=-1;
  	}
  	else
  	{
  	float slope=(dest.getY()-(float)(position.getY()))/(dest.getX()-(float)(position.getX()));
  	if(slope>1)
  	{
   		dy=20;
   		if(dest.getY()<position.getY())
    			dy*=-1;
   		dx=dy/slope;
  	}
  	else
  	{
   		dx=20;
   		if(dest.getX()<position.getX())
    			dx*=-1;
   		dy=dx*slope;
  	}
	}
  	boolean colide=false;
  	myBBox k,k2;
  	Point pnt1;
  	Point pnt2;
  	ArrayList<SceneObject> lst = new ArrayList<SceneObject>();

  	lst.addAll(s.getObstacles());
  	lst.addAll(s.getActors());

  	for(SceneObject sc : lst)
  	{
   		if(sc.getBBox().intersects( k=new myBBox(pnt1= new Point(getPosition().getX()+(int)dx,getPosition().getY()+(int)dy) ,pnt2= new Point(getPosition().getX()+(int)dx+15,getPosition().getY()+(int)dy+10)) ) )
  		{
    			colide=true;
    			break;
  		}
  		else
  		{
   			colide=false;
  		}
  	}
  	while(colide)
  	{

  		pnt1= new Point(getPosition().getX()+(int)dx,getPosition().getY()+(int)dy);
  		pnt2= new Point(getPosition().getX()+(int)dx+15,getPosition().getY()+(int)dy+10);
  		k=new myBBox(pnt1,pnt2);

  		for(SceneObject sc : lst)
  		{
   			if(sc.getBBox().intersects( k ))
   			{
    				colide=true;
    				break;
   			}
   			else
   			{
    				colide=false;
   			}
  		}
  		if(direction==0)
   			dx=-dx;     
  		else if(direction==1)
  		{
  			dx=-dx;
   			dy=-dy;
  		}
  		/*else if(direction==2)
   			dx=-dx;*/
  		else if(direction==3)
  		{
   			dx=0;
   			dy=0;
   			break;
  		}
  		direction+=1;
 	}
  	

  	for(SceneObject sc : lst)
  	{
   		if(sc.getBBox().intersects( k=new myBBox(pnt1= new Point(getPosition().getX()+(int)dx,getPosition().getY()+(int)dy) ,pnt2= new Point(getPosition().getX()+(int)dx+15,getPosition().getY()+(int)dy+10)) ) )
  		{
    			colide=true;
   			Scene.getScene().getActors().remove(this);
    			break;
  		}
  		else
   			colide=false;
	}
    	if(!colide)
    		setPosition(getPosition().getX()+(int)dx,getPosition().getY()+(int)dy);
    		direction=0;
  	if(dest.getX()==position.getX())
  	{
   	dx=0;
   	dy=20;
   	if(dest.getY()<position.getY())
    		dy*=-1;
  	}
  	else
  	{
    	float slope=(dest.getY()-(float)(position.getY()))/(dest.getX()-(float)(position.getX()));
    	if(slope>1)
    	{
     		dy=20;
     		if(dest.getY()<position.getY())
      			dy*=-1;
     		dx=dy/slope;
    	}
    	else
    	{
     		dx=20;
     		if(dest.getX()<position.getX())
      			dx*=-1;
     		dy=dx*slope;
    	}
}

  
 }
}
