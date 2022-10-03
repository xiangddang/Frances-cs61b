public class Planet{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	} 
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		double Distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		return Distance;
	}

	public double calcForceExertedBy(Planet p){
		double G = 6.67e-11;
		return G * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
	}

	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		return this.calcForceExertedBy(p) * dx / this.calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - this.yyPos;
		return this.calcForceExertedBy(p) * dy / this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] planets){
		double fx = 0.0;
		for(Planet planet : planets){
			if(planet.equals(this)){
				fx += 0;
			}else{
				fx += this.calcForceExertedByX(planet);
			}
		}
		return fx;
	}
	public double calcNetForceExertedByY(Planet[] planets){
		double fy = 0.0;
		for(Planet planet : planets){
			if(planet.equals(this)){
				fy += 0;
			}else{
				fy += this.calcForceExertedByY(planet);
			}
		}
		return fy;
	}
	public void update(double dt, double fX, double fY){
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		this.xxVel += dt * ax;
		this.yyVel += dt * ay;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}
	public void draw(){
		String pimgFileName = "images/" + this.imgFileName
		StdDraw.picture(this.xxPos, this.yyPos, pimgFileName);
	}
}