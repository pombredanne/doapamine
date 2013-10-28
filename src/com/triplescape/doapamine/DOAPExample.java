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

@Project(
	    name="DOAPExample",
	    homepage="http://www.bar.com",
	    vendor="",
	    service_endpoint="",
	    old_homepage={},
	    category="DOAP",
	    created="2005-10-15",
	    shortdesc="DOAP Annotation Example",
	    description="An example of using DOAPamine Java annoations.",
	    mailinglist="foo@bar.com",
	    license={"http://www.gnu.org/copyleft/lesser.html","http://www.gnu.org/copyleft/gpl.html"},
	    download_page="http://www.foo.com/doap",
	    download_mirror={},
	    _implements={
	    		@Specification(about="http://usefulinc.com/ns/doap")
	    },
	    language="",
	    wiki="",
	    blog="",
	    audience="",
	    bug_database="",
	    screenshots={},
	    programming_language="FooScript",
	    os={},
	    platform="",
	    release=@Version(name="unstable", created="2005-10-15", revision="0.1", file_release="", platform="", os=""),
	    maintainer={},
	    developer={
	        @Person(name="John Doe", mbox="jd@bar.com", seeAlso="http://www.bar.com/people/jd/foaf.rdf")
	        },
	    documentor={},
	    translator={},
	    tester={},
	    helper={},
	    repository=@Repository(type=Repository.type.CVSRepository, module="doap", location="cvs.foobar.com", browse="http://www.foobar.com/cvs", anon_root="www.foobar.com/cvs")
	    )
	    
public class DOAPExample {

	public static void main(String[] args) {

		System.out.println("Hello, ya big DOAP!");

	}
}
