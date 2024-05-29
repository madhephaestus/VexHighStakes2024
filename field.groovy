import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Cylinder

double cornerRad = 9.25
double edgeToEdge = 55.45
double sliceThick = 1
double shortSideTipToEdge =50.5
double towerHeight = 1172.4
double coneWidth =205
double coneHeight = 90
double coneTopDiam =66.2
double runLength = 33.5*25.4
double hang1Height = 409.606

CSG corner = new Cylinder(cornerRad, sliceThick).toCSG()
				.toXMin()
				.toYMin()
CSG edge = corner.union(corner.toYMax().movey(edgeToEdge)).hull()

CSG group = edge.union(
		corner
			.toXMax()
			.moveToCenterY()
			.movey(edge.getCenterY())
			.movex(shortSideTipToEdge)
			).hull()
CSG cone = new Cylinder(coneWidth/2, coneTopDiam/2, coneHeight).toCSG()
		.setColor(javafx.scene.paint.Color.color(0.2,0.2,0.2))
CSG cone2 = cone.movey(runLength).movex(runLength)
CSG cone3 = cone.movex(runLength)
CSG cone4 = cone.movey(runLength)
CSG pole = group.union(group.movez(towerHeight)).hull()
			.moveToCenterX()
			.moveToCenterY()
			.movex(5)
			.rotz(-45)
			//.union(cone)
			.setColor(javafx.scene.paint.Color.color(0.2,0.2,0.2))
CSG pole1=pole.rotz(90).movey(runLength)
		
CSG pole2=pole.rotz(180).movey(runLength).movex(runLength)
CSG pole3=pole.rotz(-90).movex(runLength)
//poles=poles.union(poles.rotz(180).movex(runLength).movey(runLength))

CSG rungEnd = group.roty(90).toXMin()
CSG rung = rungEnd.union(rungEnd.movex(runLength)).hull().movey(-coneTopDiam/2)
ArrayList<CSG>rungs = [	rung,
						rung.movey(runLength),
						rung.rotz(90).movey(runLength),
						rung.rotz(90).movey(runLength).movex(runLength)
						]
//CSG rungs = rung.union(rung.movey(runLength))
//rungs=rungs.union(rungs.rotz(90).movey(runLength))
//
//		
CSG Pole = new Cylinder(12.5, 100).toCSG().toZMax().movez(1267)
		.setColor(javafx.scene.paint.Color.web("#CFFF04"))
		
HashMap<String,HashMap<String,Object>> objects = ScriptingEngine.gitScriptRun(
	"https://github.com/madhephaestus/VexHighStakes2024.git",
	 "fieldLayout.json")
double x = 	objects.Robot.location.x
double y = 	objects.Robot.location.y
double z = 	objects.Robot.location.z
double rotZ =objects.Robot.location.rotZ

ArrayList<CSG> back =[pole1,pole,pole2,pole3,Pole,cone,cone2,cone3,cone4]
back.addAll(rungs.collect{it.movez(hang1Height).setColor(javafx.scene.paint.Color.color(0.2,0.2,0.2))})
back.addAll(rungs.collect{it.movez(765).setColor(javafx.scene.paint.Color.GRAY)})
back.addAll(rungs.collect{it.movez(1120).setColor(javafx.scene.paint.Color.web("#CFFF04"))})
back=back
.collect{
	it.rotz(45)
		.movex(46.64*25.4-x)
		.movey(-70.20*25.4+y)
		.rotz(rotZ)
}
double fieldLength = 12*12*25.4
back.add(new Cube(fieldLength,fieldLength,50)
	.toCSG()
	.toZMax()
	.toXMin()
	.movey(-fieldLength/2+y)
	.movex(-x)
	.rotz(rotZ)
	.setColor(javafx.scene.paint.Color.GRAY))
return back











