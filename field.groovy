import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cylinder

double cornerRad = 9.25
double edgeToEdge = 55.45
double sliceThick = 1
double shortSideTipToEdge =50.5
double towerHeight = 1172.4
double coneWidth =205
double coneHeight = 90
double coneTopDiam =66.2
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
CSG pole = group.union(group.movez(towerHeight)).hull()
			.moveToCenterX()
			.moveToCenterY()
			.movex(5)
			.union(cone)
			.setColor(javafx.scene.paint.Color.color(0.2,0.2,0.2))

return pole