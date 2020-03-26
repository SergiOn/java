plugins {
  id("java")
}

group = "com.denofprogramming"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}
repositories{
	mavenCentral()
}

dependencies { 
	implementation("org.apache.commons:commons-math3:3.2")
	testImplementation("junit:junit:4.11")
}


val jar: Jar by tasks
jar.apply {

    baseName = "${project.name}-all"  
    manifest {
        attributes["Implementation-Title"] = "Jar File -all Example"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "com.denofprogramming.random.App"
    }
    from(configurations["runtimeClasspath"].map({file -> project.zipTree(file) }))
}



