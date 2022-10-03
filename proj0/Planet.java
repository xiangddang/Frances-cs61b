public class Planet{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	} 
	public Planet(Planet p){
		double xxPos = p.xxPos;
		double yyPos = p.yyPos;
		double xxVel = p.xxVel;
		double yyVel = p.yyVel;
		double mass = p.mass;
		String imgFileName = p.imgFileName;
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
		StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
	}
}