import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Toroid

ArrayList<CSG> parts = []
double inner = 76.2/2.0
double outer = 177.8/2.0
double diff  = outer - inner
//println diff
double mass =0.122

CSG t = new  Toroid(inner, outer , 40, 40).toCSG()
			.setColor(javafx.scene.paint.Color.BLUE)
			.rotx(90)
double sliceAngle = 360/12
CSG cubeSlice = new Cube(outer*2,outer*2,diff).toCSG()
				.toXMin()
				.toYMin()
cubeSlice=cubeSlice.intersect(cubeSlice.rotz(sliceAngle*2))
for(double i=0;i<360;i+=sliceAngle){
	parts.add(t.intersect(cubeSlice.rotz(i)).movex(400))
	//parts.add(cubeSlice.rotz(i))
}
parts.forEach{it.setName("vexRing").getStorage().set("massKg", mass/parts.size());};
return parts

