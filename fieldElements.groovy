
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Toroid

// code here

ArrayList<CSG> parts = []
double inner = 76.2/2.0
double outer = 177.8/2.0
double diff  = outer - inner
println diff
outer = inner+diff/2

CSG t = new  Toroid(inner, outer , 40, 40).toCSG()
			.setColor(javafx.scene.paint.Color.BLUE)
			.rotx(90)
			
return t

