<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $name = $_POST['name'];
	$surname = $_POST['surname'];
	$phone = $_POST['phone'];
    $email = $_POST['email'];
    $id = $_POST['id'];

    require_once 'connect.php';

    $sql = "UPDATE users SET name='$name', email='$email', phone='$phone', surname='$surname' WHERE id='$id' ";

    if(mysqli_query($conn, $sql)) {

          $result["success"] = "1";
          $result["message"] = "success";

          echo json_encode($result);
          mysqli_close($conn);
      }
  }

else{

   $result["success"] = "0";
   $result["message"] = "error!";
   echo json_encode($result);

   mysqli_close($conn);
}

?>


