svp-lib
==========

Copyright (c) 2016 Universidade Federal Fluminense (UFF).
Copyright (c) 2016 Universidade Federal do Rio de Janeiro (UFRJ).

All rights reserved.

The svp-lib is a Java library that implements the Simple Virtual Partitioning (SVP) algorithm to execute a XQuery over distributed XML databases [1]. It allows a developer to easily employ the SVP technique over many parallel processing frameworks, using different XML Native Database Management Systems.

The current version supports both BaseX and Sedna XML database systems.

Team
----

The original code was written by Carla Rodrigues and was latter enhanced and adapted as a library by Gabriel Tessarolli and Luiz Matos.

* Carla Rodrigues (UFRJ)
* Gabriel Tessarolli (UFF)
* Luiz Matos (UFF and UFAC)
* Vanessa Braganholo (UFF)
* Marta Mattoso (UFRJ)

Publications
------------

* [Rodrigues, C., Braganholo, V., Mattoso, M. Virtual Partitioning ad-hoc Queries over Distributed XML Databases. Journal of Information and Data Management (JIDM): 495–510, 2011.] (https://github.com/dew-uff/svp-lib/raw/master/docs/jidm2011.pdf)
* [Silva, L., Silva Júnior, L., Mattoso, M., Braganholo, V. On the performance of the position () XPath function. Proceedings of the 2013 ACM Symposium on Document Engineering (DocEng): 229-230, 2013.] (http://dl.acm.org/citation.cfm?id=2494295)
* [Braganholo, V., Mattoso, M. A Survey on XML Fragmentation. ACM SIGMOD Record: 24–35, 2014.] (http://dl.acm.org/citation.cfm?id=2694434)

Building
--------

To build svp-lib, you need Maven installed in your system. Just run:

```bash
$ mvn package -DskipTests=true
```

The "-DskipTests=true" is necessary because our tests require BaseX and Sedna servers to be running, so they will fail if not.

You might want to add the generated jar to your local Maven repository, so you may list it as a dependency in the projects that use it. 

```bash
$ mvn install -DskipTests=true
```

Using svp-lib
-------------

Please check our [wiki] (https://github.com/dew-uff/svp-lib/wiki) for instructions and examples of usage. 


Used Software
-------------

svp-lib relies on the following libraries:

* [XQJ BaseX Library] (http://xqj.net/basex/)
* [XQJ Sedna Library] (http://xqj.net/sedna/)

License Terms
-------------

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
