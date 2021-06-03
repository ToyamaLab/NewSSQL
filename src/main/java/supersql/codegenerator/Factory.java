package supersql.codegenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import supersql.common.Log;

public class Factory {
	private LocalEnv env;
	private LocalEnv env2;
	private String classPrefix = new String();
	private Class[] args;
	
	private void initializeArgs() {
		args = new Class[3];
		try {
			Log.info(getClassPrefix());
			Class argsClass = Class.forName(getClassPrefix() + "Env");
			args[0] = Class.forName("supersql.codegenerator.Manager");
			args[1] = argsClass;
			args[2] = argsClass;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Computes the constructor of a class regarding an array 
	 * of argument classes. The args array is needed to 
	 * retrieve the right constructor in the class.
	 * Ex: getConstructor("Manager"); 
	 * returns the constructor for supersql.codegenerator.xxx.xxxManager
	 * (xxx can be HTML, XML,...)
	 * 
	 * @postfix End of the class name (Manager, Env, C0...)
	 * @return The constructor for a specific postfix
	 */
	private Constructor getConstructor(String postfix) {
		// TODO remove method initializeArgs
		initializeArgs();
		try {
			return Class.forName(getClassPrefix() + postfix).getConstructor(args);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Manager createManager() {
		try {
			Class argsClass = Class.forName(getClassPrefix() + "Env");
			args = new Class[2];
			args[0] = argsClass;
			args[1] = argsClass;
			Constructor managerConstructor = Class.forName(getClassPrefix() + "Manager").getConstructor(args);
			return (Manager) managerConstructor.newInstance(getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void createLocalEnv() {
	}

	public Connector createC0(Manager manager) {
		try {
			Constructor connectorConstructor = getConstructor("C0");
			return (Connector) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connector createC1(Manager manager) {
		try {
			Constructor connectorConstructor = getConstructor("C1");
			return (Connector) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connector createC2(Manager manager) {
		try {
			Constructor connectorConstructor = getConstructor("C2");
			return (Connector) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connector createC3(Manager manager) {
		try {
			Constructor connectorConstructor = getConstructor("C3");
			return (Connector) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connector createC4(Manager manager) {
		try {
			
			Constructor connectorConstructor = getConstructor("C4");
			return (Connector) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	//tbt add 180806
	//for join_string
	public Connector createConcat(Manager manager) {
		try {
			Constructor connectorConstructor = getConstructor("CONCAT");
			return (Connector) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	//tbt end

	public Grouper createG0(Manager manager) {
		try {
			
			Constructor connectorConstructor = getConstructor("G0");
			return (Grouper) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Grouper createG1(Manager manager) {
		try {
			
			Constructor connectorConstructor = getConstructor("G1");
			return (Grouper) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Grouper createG2(Manager manager) {
		try {
			Constructor connectorConstructor = getConstructor("G2");
			return (Grouper) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Grouper createG3(Manager manager) {
		try {
			
			Constructor connectorConstructor = getConstructor("G3");
			return (Grouper) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Grouper createG4(Manager manager) {
		try {
			
			Constructor connectorConstructor = getConstructor("G4");
			return (Grouper) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Grouper createG5(Manager manager) {
		try {
			
			Constructor connectorConstructor = getConstructor("G5");
			return (Grouper) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Decorator createDecoration(Manager manager) {
		try {
			Constructor decoratorConstructor = getConstructor("Decoration");
			return (Decorator) decoratorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Attribute createAttribute(Manager manager) {
		try {
			
			Constructor connectorConstructor = getConstructor("Attribute");
			return (Attribute) connectorConstructor.newInstance(manager, getEnv(), getEnv2());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Function createFunction(Manager manager) {
		try {
			Constructor connectorConstructor = getConstructor("Function");
			return (Function) connectorConstructor.newInstance(manager, getEnv(), getEnv2());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Attribute createConditionalAttribute(Manager manager) {
		return null;
	}
	
	public IfCondition createIfCondition(Manager manager, Attribute condition,
			TFE thenTfe, TFE elseTfe) {
		return null;
	}
/*********************
* Getters and Setters
*********************/
	public LocalEnv getEnv() {
		return env;
	}

	public void setEnv(LocalEnv env) {
		this.env = env;
	}

	public LocalEnv getEnv2() {
		return env2;
	}

	public void setEnv2(LocalEnv env2) {
		this.env2 = env2;
	}

	public String getClassPrefix() {
		return classPrefix;
	}

	public void setClassPrefix(String classPrefix) {
		this.classPrefix = classPrefix;
	}

}
