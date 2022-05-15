package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<GameObject> trail = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < trail.size(); i++) {
			if(i < trail.size()) {
				GameObject tempObject = trail.get(i);
				tempObject.tick();
			}
		}
		for(int i = 0; i < object.size(); i++) {
			if(i < object.size()) {
				GameObject tempObject = object.get(i);
				tempObject.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < trail.size(); i++) {
			if(i < trail.size()) {
				GameObject tempObject = trail.get(i);
				tempObject.render(g);
			}
		}
		for(int i = 0; i < object.size(); i++) {
			if(i < object.size()) {
				GameObject tempObject = object.get(i);
				tempObject.render(g);
			}
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void addTrail(GameObject trail) {
		this.trail.add(trail);
	}
	
	public void removeTrail(GameObject trail) {
		this.trail.remove(trail);
	}

	public void clearEnemies() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(tempObject.getID() != ID.Player) {removeObject(tempObject); i--;}
		}
	}

	public void clearAll() {
		for(int i = 0; i < trail.size(); i++) {
			GameObject tempObject = trail.get(i);
			
			removeTrail(tempObject);
			i--;
		}
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			removeObject(tempObject);
			i--;
		}
	}
}
