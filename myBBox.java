package IMT2018018;
import animation.*;
public class myBBox implements BBox
{
	private Point min;
	private Point max;
	public myBBox(Point p1,Point p2)
 	{
  		min=p1;
  		max=p2;
 	}
 	public Point getMinPt()
 	{
  		return min;
 	}
 	public Point getMaxPt()
 	{
  		return max;
 	}
 	public boolean intersects(BBox b)
 	{
  		Point pmin,pmax;
  		pmin=b.getMinPt();
  		pmax=b.getMaxPt();
  		if(max.getX()<pmin.getX())
   			return false;
  		else if(pmax.getX()<min.getX())
   			return false;
  		else if(min.getY()>pmax.getY())
   			return false;
  		else if(pmin.getY()>max.getY())
   			return false;
  		else 
   			return true;
 	}
}

  
