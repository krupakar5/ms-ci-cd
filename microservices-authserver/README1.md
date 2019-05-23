                   Securing RESTful Resources With OAuth 2.0
                   ------------------------------------------


An Introduction to OAuth 2.0
------------------------------
OAuth 2.0 is the industry-standard protocol for authorization. OAuth 2.0 supersedes the work done on the original OAuth protocol created in 2006. OAuth 2.0 focuses on client developer simplicity while providing specific authorization flows for web applications, desktop applications, mobile phones, and living room devices. This specification and its extensions are being developed within the IETF OAuth Working Group.

OAuth 2.0 terminology
----------------------

1.Resource Owner
----------------
  the entity that can grant access to a protected resource. Typically this is the end-user.

2.Application
-------------- 
an application requesting access to a protected resource on behalf of the Resource Owner.

3.Resource Server
------------------
the server hosting the protected resources. This is the API you want to access.

4.Authorization Server
----------------------

the server that authenticates the Resource Owner, and issues Access Tokens after getting proper authorization. In this case, Auth0.
User Agent: the agent used by the Resource Owner to interact with the Application, for example a browser or a native application.


