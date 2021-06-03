using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pulse : MonoBehaviour {
	float i = 1;
	bool flag = true;
	int  speed, scale;
	Vector3 localScaleAtStart;
	// Use this for initialization
	void Start () {
		localScaleAtStart = transform.localScale;  
	}
	
	// Update is called once per frame
	void Update () {
		transform.localScale = localScaleAtStart * i;
		if (i <= 1) {
			flag = true;
		} else if (i >= scale) {
			flag = false;
		}

		if (flag) {
			i += Time.deltaTime * (scale - 1) * speed; //増加速度　 i1→i2の時間=Time.deltaTime
		} else {
			i -= Time.deltaTime * (scale - 1) * speed; //減少速度
		}
	}

	public void SetPulse (int scale, int speed){
		this.scale = scale;
		this.speed = speed;
	}

		
}
 