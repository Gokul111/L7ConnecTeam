function roleChange(role){
    var modulesList=document.querySelectorAll(".moduleAll");
    modulesList.forEach(element => {
        element.style.display="none";
       
    });
   
var modulesRoleList=document.querySelectorAll("."+role+"M");
modulesRoleList.forEach(element => {
    element.style.display="block";
   
});
document.getElementById("selectedRole").innerText=role;
}

function selectedFeature(feature){
console.log(feature);
    document.getElementById("selectedFeatureInput").value=feature;
    document.getElementById("selectedFeatureForm").submit();
}

