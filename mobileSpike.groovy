import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cylinder
import eu.mihosoft.vrl.v3d.Sphere

double bottomWidth = 300
double bottomHeight = 50
double bottomRad =250

CSG weeble = new Sphere(bottomRad,60,80).toCSG().toZMin()
CSG weebleCutter = new Cylinder(bottomWidth/2, bottomHeight).toCSG()
weeble=weeble.intersect(weebleCutter)
		.setColor(javafx.scene.paint.Color.color(0.2,0.2,0.2))


return weeble