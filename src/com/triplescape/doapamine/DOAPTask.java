/*
Copyright (c) 2009, Brian Manley
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions 
are met:

- Redistributions of source code must retain the above copyright 
notice, this list of conditions and the following disclaimer.

- Redistributions in binary form must reproduce the above 
copyright notice, this list of conditions and the following 
disclaimer in the documentation and/or other materials provided 
with the distribution.

- Neither the name of the copyright holder nor the names of its 
contributors may be used to endorse or promote products derived 
from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT 
NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS 
OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.triplescape.doapamine;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import java.io.File;
import java.io.PrintWriter;

@Project(
	    name="DOAPamine",
	    homepage="https://github.com/thebrianmanley/doapamine",
	    old_homepage={},
	    category={ "http://freshmeat.net/browse/45/", "http://freshmeat.net/browse/198/" },
	    created="2009-07-02",
	    shortdesc="Ant task and annotations to create DOAP from Java.",
	    description="DOAPamine generates DOAP from custom class/package annotations.",
	    mailinglist={},
	    license={ "http://www.opensource.org/licenses/bsd-license.php" },
	    download_page="https://github.com/thebrianmanley/doapamine",
	    download_mirror={},
	    wiki="",
	    bug_database="",
	    screenshots={},
	    programming_language="java",
	    os={},
	    platform="java",
	    vendor="",
	    service_endpoint="",
	    blog="http://thebrianmanley.com",
	    audience="developers",
	    language="en",
	    release=@Version(name="stable", created="2009-06-02", revision="0.4", os="any", platform="java", file_release="https://github.com/thebrianmanley/doapamine"),
	    _implements= { 
	    	@Specification(about="http://usefulinc.com/ns/doap") 
	    	},
	    developer={
	        @Person(name="Brian Manley", mbox="brian.manley@gmail.com", seeAlso="http://thebrianmanley.com")
	        },
	    maintainer={},        
	    documentor={},
	    translator={},
	    tester={},
	    helper={},
	    repository=@Repository(type=Repository.type.GitRepository, browse="https://github.com/thebrianmanley/doapamine", anon_root="https://github.com/thebrianmanley/doapamine.git", location="https://github.com/thebrianmanley", module="daopamine")
	    )
	    
public class DOAPTask extends Task {

	private String _class = null;
	private String _output = null;

	public void setClass(String clazz) {
		_class = clazz;
	}

	public void setOutput(String dir) {
		_output = dir;
	}

	private boolean okay(String value) {
		return (value != null && value.trim().length() != 0);
	}

	private String pad(int count) {
		String pad = "";
		for (int i = 0; i < count; i++) {
			pad += "\t";
		}
		return pad;
	}

	private void emitStrings(int pad, String[] strings, String name,
			PrintWriter out) throws Exception {
		if (strings.length > 0) {
			for (int i = 0; i < strings.length; i++) {
				if (okay(strings[i])) {
					out.println(pad(pad) + "<" + name + ">" + strings[i] + "</"
							+ name + ">");
				}
			}
			out.println();
		}
	}

	private void emitString(int pad, String value, String name, PrintWriter out)
			throws Exception {

		if (okay(value)) {
			out.println(pad(pad) + "<" + name + ">" + value + "</" + name + ">");
		}

	}

	private void emitResources(int pad, String[] strings, String name,
			PrintWriter out) throws Exception {
		if (strings.length > 0) {
			for (int i = 0; i < strings.length; i++) {
				if (okay(strings[i])) {
					out.println(pad(pad) + "<" + name + " rdf:resource=\""
							+ strings[i] + "\"/>");
				}
			}
			out.println();
		}
	}

	private void emitResource(int pad, String value, String name,
			PrintWriter out) throws Exception {

		if (okay(value)) {
			out.println(pad(pad) + "<" + name + " rdf:resource=\"" + value
					+ "\"/>");
		}

	}

	public void emitPerson(int pad, Person[] people, String name,
			PrintWriter out) throws Exception {
		if (people.length > 0) {

			for (int i = 0; i < people.length; i++) {
				out.print(pad(pad) + "<" + name + ">\n");
				out.print(pad(pad + 1) + "<foaf:Person>\n");
				out.print(pad(pad + 2) + "<foaf:name>" + people[i].name()
						+ "</foaf:name>\n");
				if (people[i].mbox().startsWith("mailto:")) {
					out.print(pad(pad + 2) + "<foaf:mbox rdf:resource=\""
							+ people[i].mbox() + "\"/>\n");
				} else {
					out.print(pad(pad + 2)
							+ "<foaf:mbox rdf:resource=\"mailto:"
							+ people[i].mbox() + "\"/>\n");
				}
				out.print(pad(pad + 2) + "<rdfs:seeAlso rdf:resource=\""
						+ people[i].seeAlso() + "\"/>\n");
				out.print(pad(pad + 1) + "</foaf:Person>\n");
				out.print(pad(pad) + "</" + name + ">\n");
			}

			out.print("\n");
		}

	}

	private void emitRelease(Version release, PrintWriter out) throws Exception {

		if (okay(release.name()) || okay(release.created())
				|| okay(release.revision())) {

			out.print(pad(2) + "<Release>\n");
			out.print(pad(3) + "<version>\n");
			emitString(4, release.name(), "name", out);
			emitString(4, release.created(), "created", out);
			emitString(4, release.revision(), "revision", out);
			emitResource(4, release.file_release(), "file-release", out);
			out.print(pad(3) + "</version>\n");
			out.print(pad(2) + "</Release>\n");
			out.print("\n");

		}
	}

	private void emitRepository(Repository repos, PrintWriter out)
			throws Exception {

		if (repos.type() != Repository.type.NONE) {
			out.print(pad(2) + "<repository>\n");
			out.print(pad(3) + "<" + repos.type().toString() + ">\n");
			emitResource(4, repos.anon_root(), "anon-root", out);
			emitResource(4, repos.browse(), "browse", out);
			emitString(4, repos.module(), "module", out);
			emitResource(4, repos.location(), "location", out);
			out.print(pad(3) + "</" + repos.type().toString() + ">\n");
			out.print(pad(2) + "</repository>\n");
			out.print("\n");

		}
	}

	private void emitSpecification(Specification spec, PrintWriter out)
			throws Exception {

		if (okay(spec.about())) {
			out.print(pad(2) + "<implements>\n");
			out.print(pad(3) + "<Specification rdf:about=\"" + spec.about()
					+ "\"/>\n");
			out.print(pad(2) + "</implements>");
			out.print("\n");

		}
	}

	private void emitHeader(PrintWriter out) throws Exception {

		out.print("<?xml version=\"1.0\"?>\n");
		out.print("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n"
				+ "\txmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\n"
				+ "\txmlns:foaf=\"http://xmlns.com/foaf/0.1/\"\n"
				+ "\txmlns=\"http://usefulinc.com/ns/doap#\">\n\n");

	}

	private void emitMaker(PrintWriter out) throws Exception {

		out.print(pad(1) + "<rdf:Description rdf:about=\"\">\n");
		out.print(pad(2) + "<foaf:maker>\n");
		out.print(pad(3) + "<foaf:Agent>\n");
		out.print(pad(4) + "<foaf:name>" + DOAPTask.class.getName()
				+ "</foaf:name>\n");
		out.print(pad(4)
				+ "<foaf:homepage rdf:resource=\"http:/www.triplescape.com/doapamine\"/>\n");
		out.print(pad(3) + "</foaf:Agent>\n");
		out.print(pad(2) + "</foaf:maker>\n");
		out.print(pad(1) + "</rdf:Description>\n");
		out.print("\n");

	}

	@SuppressWarnings(value = "unchecked")
	public void execute() throws BuildException {

		if (_class == null) {
			throw new BuildException("classname is required.");
		}

		Class<?> clazz = null;

		try {

			clazz = Class.forName(_class);

		} catch (Exception e) {
			throw new BuildException("Could not load class " + _class);
		}

		if (!clazz.isAnnotationPresent(Project.class)) {
			throw new BuildException("No Project annotation present in class "
					+ _class);
		}

		System.out.println("Writing DOAP data to " + _output);

		Project project = (Project) clazz.getAnnotation(Project.class);

		try {

			PrintWriter out = new PrintWriter(new File(_output));

			emitHeader(out);

			out.println("\t<Project>\n");

			emitString(2, project.name(), "name", out);
			emitResource(2, project.homepage(), "homepage", out);
			emitResources(2, project.old_homepage(), "old-homepage", out);
			emitString(2, project.shortdesc(), "shortdesc", out);
			emitString(2, project.description(), "description", out);

			Repository repo = project.repository();
			emitRepository(repo, out);

			Version[] versions = project.release();
			for (int i = 0; i < versions.length; i++) {
				emitRelease(versions[i], out);
			}

			Specification[] specs = project._implements();
			for (int i = 0; i < specs.length; i++) {
				emitSpecification(specs[i], out);
			}

			emitResources(2, project.mailinglist(), "mailinglist", out);
			emitResource(2, project.download_page(), "download-page", out);
			emitResource(2, project.vendor(), "vendor", out);
			emitResource(2, project.wiki(), "wiki", out);
			emitResource(2, project.blog(), "blog", out);
			emitResource(2, project.bug_database(), "bug-database", out);

			emitResources(2, project.download_mirror(), "download-mirror", out);
			emitResources(2, project.license(), "license", out);
			emitResources(2, project.category(), "category", out);
			emitResources(2, project.screenshots(), "screenshots", out);
			emitStrings(2, project.programming_language(),
					"programming-language", out);
			emitStrings(2, project.os(), "os", out);
			emitStrings(2, project.language(), "language", out);
			emitStrings(2, project.platform(), "platform", out);
			emitStrings(2, project.audience(), "audience", out);
			emitResource(2, project.service_endpoint(), "service-endpoint", out);

			emitPerson(2, project.maintainer(), "maintainer", out);
			emitPerson(2, project.developer(), "developer", out);
			emitPerson(2, project.documentor(), "documentor", out);
			emitPerson(2, project.translator(), "translator", out);
			emitPerson(2, project.tester(), "tester", out);
			emitPerson(2, project.helper(), "helper", out);

			out.print("\t</Project>\n");

			emitMaker(out);

			out.print("</rdf:RDF>\n");

			out.flush();
			out.close();

		} catch (Exception e) {
			throw new BuildException("Failed to create file. " + e.getMessage());
		}

	}

	private void printUsage() {
		System.out
				.println("Usage: DOAPTask [annotated class name] [output file name]");
	}

	public static void main(String[] args) {
		DOAPTask task = new DOAPTask();
		if (args.length != 2) {
			task.printUsage();
			System.exit(1);
		}
		task.setClass(args[0]);
		task.setOutput(args[1]);
		task.execute();
	}
}
