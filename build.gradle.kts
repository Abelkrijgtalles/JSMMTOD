import net.fabricmc.loom.task.FabricModJsonV1Task

plugins {
	id("net.fabricmc.fabric-loom")
	id("maven-publish")
}

val minecraft_version: String by project
val loader_version: String by project

val mod_version: String by project
val maven_group: String by project

val cloth_config_version: String by project
val fabric_api_version: String by project
val mod_menu_version: String by project

repositories {
	maven("https://maven.shedaniel.me/")
	maven("https://maven.terraformersmc.com/") {
		name = "Terraformers"
	}
}

loom {
	splitEnvironmentSourceSets()

	mods {
		register(rootProject.name) {
			sourceSet(sourceSets["client"])
			sourceSet(sourceSets["main"])
		}
	}

}

fabricApi {
	configureDataGeneration {
		client = true
	}
}

dependencies {
	// To change the versions see the gradle.properties file
	minecraft("com.mojang:minecraft:${minecraft_version}")
	
	implementation("net.fabricmc:fabric-loader:${loader_version}")

	implementation("me.shedaniel.cloth:cloth-config-fabric:$cloth_config_version")
	implementation("net.fabricmc.fabric-api:fabric-api:$fabric_api_version")
	implementation("com.terraformersmc:modmenu:$mod_menu_version")
	
}

tasks.withType<JavaCompile>().configureEach {
	options.release.set(25)
}

java {
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
}

tasks.named<Jar>("jar") {
	val projectName = project.name
	inputs.property("projectName", projectName)

	from("LICENSE") {
		rename { "${it}_$projectName" }
	}
}

tasks.register("generateModJson", FabricModJsonV1Task::class) {
	description = "Generate fabric.mod.json"
	outputFile = file("$projectDir/src/client/resources/fabric.mod.json")

	json {
		modId = rootProject.name
		version = mod_version
		name = "JUST SHOW ME MY TIME OF DAY!"
		description = "Shows your time of day in the debug screen. That's it."
		contactInformation.put("homepage", "https://github.com/Abelkrijgtalles/JSMMTOD")
		contactInformation.put("sources", "https://github.com/Abelkrijgtalles/JSMMTOD")
		contactInformation.put("issues", "https://github.com/Abelkrijgtalles/JSMMTOD/issues")
		contactInformation.put("modrinth", "")
		author("Abelpro678") {
			contactInformation.put("homepage", "https://github.com/Abelkrijgtalles")
			contactInformation.put("modrinth", "https://modrinth.com/user/Abelpro678")
		}
		// Add contributors once I have them
		licenses.add("GPL-3.0-or-later")
		icon("assets/jsmmtod/icon.png")
		environment = "client"
		entrypoint("client", "nl.abelkrijgtalles.jsmmtod.JSMMTOD")
		entrypoint("fabric-datagen", "nl.abelkrijgtalles.jsmmtod.datagen.JSMMTODDataGenerator")
		entrypoint("modmenu", "nl.abelkrijgtalles.jsmmtod.config.JSMMTODModMenuIntegration")
		mixin("jsmmtod.client.mixins.json") {
			environment = "client"
		}
		depends("fabricloader", ">=$loader_version")
		depends("java", ">=25")
		// Probably won't cause issues using *
		recommends("cloth-config", "*")
		recommends("modmenu", "*")
	}
}

// Make sure generateModJson and runDatagen get run when building the mod
tasks.withType<ProcessResources>().configureEach {
	dependsOn(tasks.named<FabricModJsonV1Task>("generateModJson"))
}

tasks.withType<Jar>().configureEach {
	dependsOn(tasks.named<FabricModJsonV1Task>("generateModJson"))
	dependsOn(tasks.named("runDatagen"))
}

tasks.build {
	dependsOn(tasks.named("runDatagen"))
}

tasks.named("runDatagen") {
	mustRunAfter(tasks.processResources)
}