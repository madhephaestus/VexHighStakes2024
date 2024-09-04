import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Toroid

ArrayList<CSG> parts = []
HashMap<String,HashMap<String,Object>> objects = ScriptingEngine.gitScriptRun(
	"https://github.com/madhephaestus/VexHighStakes2024.git",
	 "simpleField.json")
//HashMap<String,HashMap<String,Object>> objects = ScriptingEngine.gitScriptRun(
//	"https://github.com/madhephaestus/VexHighStakes2024.git",
//	 "fieldLayout.json")
double robotx = 	toNumber(objects.Robot.location.x)
double roboty = 	toNumber(objects.Robot.location.y)
double robotz = 	toNumber(objects.Robot.location.z)
double rotZ   =		toNumber(objects.Robot.location.rotZ)
double fieldLength = 12*12*25.4

double toNumber(Object value) {
	if(value==null)
		return 0;
	if(String.class.isInstance(value)) {
		
		Object ret =ScriptingEngine.inlineScriptStringRun("return "+value.toString(), null, "Groovy")
		//println "Executing..."+value+" resulted in "+ret
		return Double.parseDouble(ret.toString())
	}else
		return Double.parseDouble(value.toString())
}
for(String key:objects.keySet()) {
	println key
	HashMap<String,Object> elements = objects.get(key);
	String git = elements.get("scriptGit")
	String file = elements.get("scriptFile")
	if(git==null||file==null)
		continue;
	ArrayList<HashMap<String,Number>> locations=elements.get("locations")
	ArrayList<CSG> elementCSG = ScriptingEngine.gitScriptRun(git,file)
	int elementCount=0;
	for(HashMap<String,Number> loc:locations) {
		elementCount++
		double x = toNumber(loc.x)-robotx
		double y = -toNumber(loc.y)+roboty
		double z = toNumber(loc.z)
		println "Values "+x+" "+y+ " "+z
		String color =loc.get("color")
		//starting.syncProperties(origin);
		ArrayList<CSG> elementCSGMoved = elementCSG.collect{
			CSG moved = it.hull().rotz(1).move(x,y,z).rotz(rotZ)
			moved.syncProperties(it);
			moved.setName(key+"_"+elementCount)
			if(color!=null)
				moved.setColor(javafx.scene.paint.Color.web(color))
			return moved
		}
		parts.addAll(elementCSGMoved)
	}
	
}

return parts

