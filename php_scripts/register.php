<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

	$name = $_POST['name'];
	$surname = $_POST['surname'];
	$email = $_POST['email'];
	$password = $_POST['password'];
	$phone = $_POST['phone'];
	$role = $_POST['role'];

	$password = password_hash($password, PASSWORD_DEFAULT);

	require_once 'connect.php';
	
	$sql = "INSERT INTO users (name, surname, email, password, phone, role) VALUES ('$name', '$surname', '$email', '$password', '$phone', '$role')";
	
	if(mysqli_query($conn, $sql)){
		$result["success"] = "1";
		$result["message"] = "success";
		
		echo json_encode($result);
		mysqli_close($conn);
	} else {
		$result["success"] = "0";
		$result["message"] = "error";
		
		echo json_encode($result);
		mysqli_close($conn);
	}
	
}

?>