import eu.mihosoft.vrl.v3d.*
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

CSG weeble = new Sphere(bottomRad,101,50).toCSG().toZMin()

CSG weebleCutter = new Hexagon(bottomWidth, bottomHeight).toCSG()
weeble=weeble.intersect(weebleCutter)
		.setColor(javafx.scene.paint.Color.color(0.2,0.2,0.2))
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

ArrayList<CSG> back = [weeble,Pole]

double sliceAngle = 360/6
CSG cubeSlice = new Cube(bottomWidth*2,bottomWidth*2,bottomHeight*4).toCSG()
				.toXMin()
				.toYMin()
cubeSlice=cubeSlice.intersect(cubeSlice.rotz(90-sliceAngle))
for(double i=0;i<360;i+=sliceAngle){
	back.add(lip.intersect(cubeSlice.rotz(i)))
}

return back.collect{it.setName("MobileSpike")}