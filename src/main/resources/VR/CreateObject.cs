using System.Collections;
using System.Collections.Generic;
using System.Xml;
using UnityEngine;

public class CreateObject : MonoBehaviour
{
	
	// Use this for initialization
	void Start ()
	{
		
	}
	
	// Update is called once per frame
	void Update ()
	{
		
	}

	public static GameObject MakeObject (XmlElement element)
	{
		GameObject obj = null;

		string text = element.InnerText;

		if (element.Name.Equals ("cube")) {
			float attribute = float.Parse (element.GetAttribute ("size"));
			obj = GameObject.CreatePrimitive (PrimitiveType.Cube);
			obj.transform.position = new Vector3 (0, 0, 0);
			obj.transform.localScale = new Vector3 (1, 1, 1) * attribute;	// オブジェクトのサイズ変更
		} else if (element.Name.Equals ("torus")) {
			float r1 = float.Parse (element.GetAttribute ("r1"));
			float r2 = float.Parse (element.GetAttribute ("r2"));
			obj = Torus.Maketorus (r1, r2);
			obj.transform.position = new Vector3 (0, 0, 0);
		} else if (element.Name.Equals ("cuboid")) {
			float l_size = float.Parse (element.GetAttribute ("l_size"));
			float w_size = float.Parse (element.GetAttribute ("w_size"));
			float d_size = float.Parse (element.GetAttribute ("d_size"));
			obj = GameObject.CreatePrimitive (PrimitiveType.Cube);
			obj.transform.position = new Vector3 (0, 0, 0);
			obj.transform.localScale = new Vector3 (l_size, w_size, d_size);
		} else if (element.Name.Equals ("pyramid")) {
			float height = float.Parse (element.GetAttribute ("height"));
			float size = float.Parse (element.GetAttribute ("size"));
			obj = Pyramid.MakePyramid (size, height);
			obj.transform.position = new Vector3 (0, 0, 0);
		} else if (element.Name.Equals ("sphere")){
			float attribute = float.Parse (element.GetAttribute ("size"));
			obj = GameObject.CreatePrimitive (PrimitiveType.Sphere);
			obj.transform.position = new Vector3 (0, 0, 0);
			obj.transform.localScale = new Vector3 (1, 1, 1) * attribute;	
		} else if (element.Name.Equals ("element")){
			//kotani's stuff	
			obj = Instantiate(Resources.Load(element.SelectNodes("id")[0].InnerText)) as GameObject;
		}

		foreach (XmlElement child in element) {
			if (child.Name.Equals ("color")) {
				ChangeColor (child.InnerText, obj);
			} else if (child.Name.Equals ("rotate")) {
				float r_x = float.Parse (child.GetAttribute ("x"));
				float r_y = float.Parse (child.GetAttribute ("y"));
				float r_z = float.Parse (child.GetAttribute ("z"));
				Rotate rot = obj.AddComponent<Rotate> () as Rotate;
				rot.SetRotation (r_x, r_y, r_z);
			} else if (child.Name.Equals ("pulse")) {
				int p_speed = int.Parse (child.GetAttribute ("speed"));
				int p_scale = int.Parse (child.GetAttribute ("scale"));
				Pulse pul = obj.AddComponent<Pulse> () as Pulse;
				pul.SetPulse (p_scale, p_speed);
			} else if (child.Name.Equals ("hop")) {
				int h_speed = int.Parse (child.GetAttribute ("speed"));
				int h_top = int.Parse (child.GetAttribute ("top"));
				string h_axis = child.GetAttribute ("axis");
				Hopping hop = obj.AddComponent<Hopping> ();
				hop.SetHopping (h_top, h_speed, h_axis);
			}
		}
		return obj;
	}

	static void ChangeColor (string color, GameObject obj)
	{
		if (System.Text.RegularExpressions.Regex.IsMatch (color, @"(\d+\.?\d*,){3}(\d+\.?\d*)")) {
			print ("getcolor");
			char[] delimiterChar = { ',' };
			string[] words = color.Split (delimiterChar);

			float r = float.Parse (words [0]); 
			float g = float.Parse (words [1]); 
			float b = float.Parse (words [2]); 
			float a = float.Parse (words [3]); 
			obj.GetComponent<MeshRenderer> ().material.color = new Color (r, g, b, a);
			//Regex usefull
		} else {
			switch (color) {
			case "red":
				obj.GetComponent<MeshRenderer> ().material.color = Color.red;
				break;
			case "blue":
				obj.GetComponent<MeshRenderer> ().material.color = Color.blue;
				break;
			case "green":
				obj.GetComponent<MeshRenderer> ().material.color = Color.green;
				break;
			case "black":
				obj.GetComponent<MeshRenderer> ().material.color = Color.black;
				break;
			case "clear":
				obj.GetComponent<MeshRenderer> ().material.color = Color.clear;
				break;
			case "cyan":
				obj.GetComponent<MeshRenderer> ().material.color = Color.cyan;
				break;
			case "gray":
				obj.GetComponent<MeshRenderer> ().material.color = Color.gray;
				break;
			case "grey":
				obj.GetComponent<MeshRenderer> ().material.color = Color.grey;
				break;
			case "yellow":
				obj.GetComponent<MeshRenderer> ().material.color = Color.yellow;
				break;
			case "white":
				obj.GetComponent<MeshRenderer> ().material.color = Color.white;
				break;
			case "magenta":
				obj.GetComponent<MeshRenderer> ().material.color = Color.magenta;
				break;
			default:
				obj.GetComponent<MeshRenderer> ().material.color = Color.white;
				break;
			}
		}
	}
}

