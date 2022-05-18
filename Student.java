class Student {
   public String name;
   public int age;
   Student(){
      this.name = "Rama";
      this.age = 29;
   }
   Student(String name, int age){
      this.name = name;
      this.age = age;
   }
   public void display() {
      System.out.println("name: "+this.name);
      System.out.println("age: "+this.age);
   }
}