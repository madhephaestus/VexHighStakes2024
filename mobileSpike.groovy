import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cylinder
import eu.mihosoft.vrl.v3d.Hexagon
import eu.mihosoft.vrl.v3d.Sphere

double bottomWidth = 254
double bottomHeight = 30.1
double bottomRad =410
double lipHeight = 44.45-bottomHeight
double lipInnerWidth =190.5
double totalHeight = 368.3-bottomHeight
double massKG = 0.948

CSG weeble = new Sphere(bottomRad,100,100).toCSG().toZMin()
CSG weebleCutter = new Hexagon(bottomWidth, bottomHeight).toCSG()
weeble=weeble.intersect(weebleCutter)
		.setColor(javafx.scene.paint.Color.GOLD)//color(0.2,0.2,0.2))
CSG lipOuter = new Hexagon(bottomWidth,lipHeight).toCSG()
CSG lipInner = new Hexagon(lipInnerWidth,lipHeight).toCSG()
CSG lip = lipOuter.difference(lipInner)
				.movez(bottomHeight)
				.setColor(javafx.scene.paint.Color.web("#CFFF04"))
CSG Pole = new Cylinder(12.5, totalHeight).toCSG().movez(bottomHeight)
				.setColor(javafx.scene.paint.Color.web("#CFFF04"))
weeble.setMassKG(massKG-0.02)
Pole.setMassKG(0.01)
lip.setMassKG(0.01)

return [weeble,lip,Pole].collect{it.setName("MobileSpike")}