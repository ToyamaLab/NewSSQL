using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Hopping : MonoBehaviour {

	float i = 0;
	bool flag = true;
	int  speed, top;
	Vector3 axis;
	Vector3 positionAtStart;
	// Use this for initialization
	void Start () {
		positionAtStart = transform.position;  
	}

	// Update is called once per frame
	void Update () {
		transform.position = positionAtStart + (axis * i);
		if (i <= 0) {
			flag = true;
		} else if (i >= top) {
			flag = false;
		}

		if (flag) {
			i += Time.deltaTime * top * speed; //増加速度　 i1→i2の時間=Time.deltaTime
		} else {
			i -= Time.deltaTime * top * speed; //減少速度
		}
	}

	public void SetHopping (int top, int speed, string axis){
		this.top = top;
		this.speed = speed;
		switch (axis) {
		case "x":
			this.axis = Vector3.right;
			break;
		case "y":
			this.axis = Vector3.up;
			break;
		case "z":
			this.axis = Vector3.forward;
			break;
		}
	}


}