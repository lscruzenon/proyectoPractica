<%@page import="bd.Conexion"%>

<%
    String nombree=request.getParameter("nombre");
    String edadd=request.getParameter("edad");
    String direccionn=request.getParameter("direccion");
    String empresaa=request.getParameter("empresa");
    // convierte=Integer.valueOf(edadd).intValue();
    Conexion dat=new Conexion(); 
    dat.ingresaDatos(nombree,edadd,direccionn,empresaa );
    out.println(dat.ver_datos());
%>
