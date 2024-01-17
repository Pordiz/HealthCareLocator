package com.example.locatorappforhealthcare;


public class DecisionTreeBuilder {

    public TreeNode buildDecisionTree() {
        TreeNode root = new TreeNode("Hello! I'm Health Mate, your medical assistant. I can help you find the right doctor or medical service based on your needs. Please choose a category:");

        TreeNode specializedDoctors = new TreeNode("Specialized Doctors");
        TreeNode medicalServices = new TreeNode("Medical Services");
        TreeNode justBrowsing = new TreeNode("Just Browsing");

        specializedDoctors.setAdditionalQuestion("Great! Please select a specialized doctor from the list below:");
        medicalServices.setAdditionalQuestion("Excellent! Please select a medical service from the list below:");

        TreeNode cardiologist = new TreeNode("Cardiologist");
        TreeNode dentist = new TreeNode("Dentist");
        TreeNode familyPhysician = new TreeNode("Family Physician");
        TreeNode pediatrician = new TreeNode("Pediatrician");
        TreeNode obsgyne = new TreeNode("Obstetrician and Gynecologist");

        TreeNode animalBiteVaccine = new TreeNode("Animal Bite Vaccine");
        TreeNode antiTetanus = new TreeNode("Anti Tetanus");
        TreeNode childrenCheckUp = new TreeNode("Children Check up");
        TreeNode emergencyCare = new TreeNode("Emergency Care");
        TreeNode generalCheckUp = new TreeNode("General Check up");
        TreeNode prenatalCheckUp = new TreeNode("Prenatal Check up");

        cardiologist.setAdditionalQuestion("What type of heart-related service do you need?");
        dentist.setAdditionalQuestion("What type of dental service are you looking for?");
        familyPhysician.setAdditionalQuestion("What type of service do you need from a family physician?");
        pediatrician.setAdditionalQuestion("What type of service does your child need from a pediatrician?");
        obsgyne.setAdditionalQuestion("What type of service do you need from an obstetrician and gynecologist?");

        specializedDoctors.addChild(dentist);
        specializedDoctors.addChild(cardiologist);
        specializedDoctors.addChild(familyPhysician);
        specializedDoctors.addChild(pediatrician);
        specializedDoctors.addChild(obsgyne);

        animalBiteVaccine.setAdditionalQuestion("What type of animal bite do you need a vaccine for?");
        antiTetanus.setAdditionalQuestion("What is the reason you need an Anti Tetanus vaccination?");
        childrenCheckUp.setAdditionalQuestion("What is the main purpose of the child's check-up?");
        emergencyCare.setAdditionalQuestion("What type of emergency care do you need?");
        generalCheckUp.setAdditionalQuestion("What is the main reason for your general check-up?");
        prenatalCheckUp.setAdditionalQuestion("What is the main purpose of your prenatal check-up?");

        TreeNode chestDiscomfort = new TreeNode("Chest pain or discomfort");
        TreeNode irregularHeart = new TreeNode("Irregular Heartbeat");
        TreeNode highBlood = new TreeNode("High Blood Pressure");
        TreeNode shortnessBreath = new TreeNode("Shortness of breath");
        TreeNode other4 = new TreeNode("Other");

        cardiologist.addChild(chestDiscomfort);
        cardiologist.addChild(irregularHeart);
        cardiologist.addChild(highBlood);
        cardiologist.addChild(shortnessBreath);
        cardiologist.addChild(other4);

        chestDiscomfort.setAdditionalQuestion("If you're experiencing chest pain or discomfort, it's important to consult a cardiologist as soon as possible. They will assess your condition and may recommend tests or treatments based on their evaluation. You can use our app to locate the top 3 nearest healthcare facilities that offer cardiologist services.");
        irregularHeart.setAdditionalQuestion("An irregular heartbeat could be a sign of an underlying issue. A cardiologist can help determine the cause and recommend appropriate treatment or management strategies. You can use our app to locate the top 3 nearest healthcare facilities that offer cardiologist services.");
        highBlood.setAdditionalQuestion("Managing high blood pressure is essential for maintaining heart health. A cardiologist can evaluate your condition, prescribe medication if necessary, and suggest lifestyle changes to help control your blood pressure. You can use our app to locate the top 3 nearest healthcare facilities that offer cardiologist services.");
        shortnessBreath.setAdditionalQuestion("Shortness of breath can be a symptom of various heart-related issues. A cardiologist can help identify the cause and recommend treatment options. You can use our app to locate the top 3 nearest healthcare facilities that offer cardiologist services.");
        other4.setAdditionalQuestion("If your symptoms don't fit into one of the above categories, it's still important to seek medical attention right away. You can use our app to locate the top 3 nearest healthcare facilities that offer cardiologist services for a wide range of heart-related conditions.");

        TreeNode toothExtraction = new TreeNode("Tooth Extraction");
        TreeNode teethCleaning = new TreeNode("Teeth Cleaning");
        TreeNode routineCheckup = new TreeNode("Routine Checkup");
        TreeNode dentalProsthetics = new TreeNode("Dental implants or Prosthetics");

        dentist.addChild(toothExtraction);
        dentist.addChild(teethCleaning);
        dentist.addChild(routineCheckup);
        dentist.addChild(dentalProsthetics);

        toothExtraction.setAdditionalQuestion("If you need to have a tooth extracted, your dentist can perform the procedure safely and with minimal discomfort. You may need an extraction if you have a severely damaged or decayed tooth, or if your tooth is causing crowding or alignment issues. You can use our app to locate the top 3 nearest healthcare facilities that offer dental services for tooth extractions. ");
        teethCleaning.setAdditionalQuestion("Regular teeth cleaning is important for maintaining good oral health and preventing gum disease. During a cleaning, your dentist will remove plaque and tartar buildup from your teeth and gums. You can use our app to locate the top 3 nearest healthcare facilities that offer dental services for teeth cleaning.");
        routineCheckup.setAdditionalQuestion(": Regular dental checkups are important for catching and treating oral health issues before they become more serious. During a checkup, your dentist will perform a thorough exam of your teeth and gums and address any concerns you may have. You can use our app to locate the top 3 nearest healthcare facilities that offer dental services for routine checkups.");
        dentalProsthetics.setAdditionalQuestion("If you're missing one or more teeth, dental implants or prosthetics can be an effective solution for restoring your smile and improving your oral health. Your dentist can evaluate your individual needs and recommend the best treatment options. You can use our app to locate the top 3 nearest healthcare facilities that offer dental services for dental implants or prosthetics.");

        TreeNode illnessConcern2 = new TreeNode("Illness or specific concern");
        TreeNode preventiveCare = new TreeNode("Vaccination or preventive care");
        TreeNode chronicDisease = new TreeNode("Chronic disease management");
        TreeNode other5 = new TreeNode("Other");

        familyPhysician.addChild(illnessConcern2);
        familyPhysician.addChild(preventiveCare);
        familyPhysician.addChild(chronicDisease);
        familyPhysician.addChild(other5);

        illnessConcern2.setAdditionalQuestion("If you're feeling ill or have a specific health concern, your family physician can provide medical care and advice. Your physician can evaluate your symptoms and recommend the best course of treatment. You can use our app to locate the top 3 nearest healthcare facilities that offer family medicine services for illness or specific concerns.");
        preventiveCare.setAdditionalQuestion("Vaccinations and preventive care are important for maintaining good health and preventing illnesses. Your family physician can provide vaccinations and other preventive care services, such as regular checkups and health screenings. You can use our app to locate the top 3 nearest healthcare facilities that offer family medicine services for vaccinations and preventive care.");
        chronicDisease.setAdditionalQuestion(" If you have a chronic condition, such as diabetes or heart disease, your family physician can help you manage your condition and improve your quality of life. Your physician can provide ongoing medical care and work with you to develop a personalized treatment plan. You can use our app to locate the top 3 nearest healthcare facilities that offer family medicine services for chronic disease management.");
        other5.setAdditionalQuestion("If your health concern doesn't fit into one of the above categories, your family physician can still provide medical care and advice. You can use our app to locate the top 3 nearest healthcare facilities that offer family medicine services for a wide range of health concerns.");

        TreeNode routineVisit3 = new TreeNode("Routine Visit");
        TreeNode vaccination = new TreeNode("Vaccination");
        TreeNode illnessConcern3 = new TreeNode("Illness or specific concern");
        TreeNode developmentalEval = new TreeNode("Developmental milestone evaluation");

        pediatrician.addChild(routineVisit3);
        pediatrician.addChild(vaccination);
        pediatrician.addChild(illnessConcern3);
        pediatrician.addChild(developmentalEval);

        routineVisit3.setAdditionalQuestion(" Regular checkups are important for your child's overall health and development. During a routine visit, your pediatrician will perform a physical exam, check your child's growth and development, and provide health education as needed. You can use our app to locate the top 3 nearest healthcare facilities that offer pediatric services for routine visits.");
        vaccination.setAdditionalQuestion(" Vaccinations are an important part of your child's healthcare and are recommended to prevent serious illnesses. Your pediatrician can provide vaccinations and help you stay on schedule with your child's vaccine appointments. You can use our app to locate the top 3 nearest healthcare facilities that offer pediatric services for vaccinations.");
        illnessConcern3.setAdditionalQuestion("If your child is experiencing an illness or specific concern, schedule an appointment with a pediatrician for evaluation and treatment. You can use our app to locate the top 3 nearest healthcare facilities that offer pediatric services for illness or specific concerns.");
        developmentalEval.setAdditionalQuestion("Pediatricians can assess your child's developmental milestones and identify any potential delays. Schedule an appointment if you have concerns about your child's development.  You can use our app to locate the top 3 nearest healthcare facilities that offer pediatric services for developmental milestone evaluations.");

        TreeNode pregnancyChild = new TreeNode("Pregnancy or Childbirth");
        TreeNode gynecologicalIssue = new TreeNode("General gynecological issue");
        TreeNode menopauseManage = new TreeNode("Menopause management");
        TreeNode other6 = new TreeNode("Other");

        obsgyne.addChild(pregnancyChild);
        obsgyne.addChild(gynecologicalIssue);
        obsgyne.addChild(menopauseManage);
        obsgyne.addChild(other6);

        pregnancyChild.setAdditionalQuestion(" If you're pregnant or planning to become pregnant, your obstetrician-gynecologist (OB/GYN) can provide comprehensive care throughout your pregnancy and childbirth. Your OB/GYN can monitor your health and the health of your baby, and provide guidance on a range of pregnancy-related concerns. You can use our app to locate the top 3 nearest healthcare facilities that offer obstetric and gynecologic services for pregnancy and childbirth.");
        gynecologicalIssue.setAdditionalQuestion(" Your OB/GYN can provide medical care and advice for a range of gynecological issues, such as menstrual irregularities, pelvic pain, or vaginal infections. Your OB/GYN can evaluate your symptoms and recommend the best course of treatment. You can use our app to locate the top 3 nearest healthcare facilities that offer obstetric and gynecologic services for general gynecological issues.");
        menopauseManage.setAdditionalQuestion("Menopause is a natural part of aging, but it can cause a range of physical and emotional symptoms. Your OB/GYN can provide medical care and advice for managing menopause symptoms and staying healthy during this life transition. You can use our app to locate the top 3 nearest healthcare facilities that offer obstetric and gynecologic services for menopause management.");
        other6.setAdditionalQuestion("If your health concern doesn't fit into one of the above categories, your OB/GYN can still provide medical care and advice. You can use our app to locate the top 3 nearest healthcare facilities that offer obstetric and gynecologic services for a wide range of health concerns.");

        TreeNode dogBite = new TreeNode("Dog bite");
        TreeNode catBite = new TreeNode("Cat bite");
        TreeNode wildAnimal = new TreeNode("Wild animal bite");
        TreeNode other1 = new TreeNode("Other");

        animalBiteVaccine.addChild(dogBite);
        animalBiteVaccine.addChild(catBite);
        animalBiteVaccine.addChild(wildAnimal);
        animalBiteVaccine.addChild(other1);

        dogBite.setAdditionalQuestion(" If you or someone you know has been bitten by a dog, it's important to seek medical attention right away. Even if the bite doesn't appear to be serious, it can still lead to infection or other complications. In addition to getting medical help, it's also important to report the bite to the appropriate authorities, such as animal control or your local health department. You can also use our app to locate the top 3 nearest healthcare facilities that offer animal bite vaccines.");
        catBite.setAdditionalQuestion("Like dog bites, cat bites can also lead to infection and other complications. If you've been bitten by a cat, you should clean the wound thoroughly with soap and water and seek medical attention if necessary. It's also important to watch for signs of infection, such as redness, swelling, or pus around the wound. You can also use our app to locate the top 3 nearest healthcare facilities that offer animal bite vaccines.");
        wildAnimal.setAdditionalQuestion("If you've been bitten by a wild animal, such as a raccoon or bat, it's important to seek medical attention right away. Wild animals can carry diseases like rabies, which can be life-threatening if left untreated. In addition to getting medical help, it's also important to report the bite to the appropriate authorities, such as animal control or your local health department. You can also use our app to locate the top 3 nearest healthcare facilities that offer animal bite vaccines.");
        other1.setAdditionalQuestion("If the animal bite doesn't fit into one of the above categories, it's still important to seek medical attention and report the bite to the appropriate authorities. Depending on the circumstances, you may also want to seek legal advice or file a claim with your insurance company. You can also use our app to locate the top 3 nearest healthcare facilities that offer animal bite vaccines.");

        TreeNode rustyObject = new TreeNode("Injury with a rusty object");
        TreeNode deepWound = new TreeNode("Deep wound or Puncture");
        TreeNode severeBurn = new TreeNode("Severe burn");
        TreeNode other2 = new TreeNode("Other");

        antiTetanus.addChild(rustyObject);
        antiTetanus.addChild(deepWound);
        antiTetanus.addChild(severeBurn);
        antiTetanus.addChild(other2);

        rustyObject.setAdditionalQuestion("If you've been injured with a rusty object, such as a nail or piece of metal, it's important to seek medical attention right away. Rusty objects can harbor tetanus bacteria, which can cause a serious and potentially life-threatening infection. In addition to getting medical help, you should also make sure you're up-to-date on your tetanus vaccine. You can use our app to locate the top 3 nearest healthcare facilities that offer anti-tetanus vaccine.");
        deepWound.setAdditionalQuestion(" Deep wounds or punctures, such as those caused by animal bites or knives, can also put you at risk for tetanus. It's important to seek medical attention right away to clean and dress the wound, and to make sure you're up-to-date on your tetanus vaccine. You can use our app to locate the top 3 nearest healthcare facilities that offer anti-tetanus vaccine.");
        severeBurn.setAdditionalQuestion("Severe burns can also put you at risk for tetanus, especially if the burn is deep or involves a large area of the body. If you've suffered a severe burn, it's important to seek medical attention right away to assess the extent of the injury and to determine whether you need a tetanus vaccine. You can use our app to locate the top 3 nearest healthcare facilities that offer anti-tetanus vaccine.");
        other2.setAdditionalQuestion("If your injury doesn't fit into one of the above categories, it's still important to seek medical attention and to discuss with your healthcare provider whether you need a tetanus vaccine. You can use our app to locate the top 3 nearest healthcare facilities that offer anti-tetanus vaccine.");

        TreeNode routineVisit = new TreeNode("Routine Visit");
        TreeNode illnessConcern = new TreeNode("Illness or specific concern");
        TreeNode developmentalIssue = new TreeNode("Developmental milestone evaluation");
        TreeNode vaccinationVisit = new TreeNode("Vaccination");

        childrenCheckUp.addChild(routineVisit);
        childrenCheckUp.addChild(illnessConcern);
        childrenCheckUp.addChild(developmentalIssue);
        childrenCheckUp.addChild(vaccinationVisit);

        routineVisit.setAdditionalQuestion("Regular checkups are important for your child's overall health and well-being. During a routine visit, your child's healthcare provider will perform a physical exam, check their growth and development, and address any concerns you may have. You can use our app to locate the top 3 nearest healthcare facilities that offer routine children checkups.");
        illnessConcern.setAdditionalQuestion("If your child is sick or has a specific concern, it's important to seek medical attention right away. Your child's healthcare provider can assess their symptoms and determine the best course of treatment. You can use our app to locate the top 3 nearest healthcare facilities that offer children checkups for illnesses and specific concerns.");
        developmentalIssue.setAdditionalQuestion(" Monitoring your child's developmental milestones is important to ensure they're growing and developing as they should. Your child's healthcare provider can evaluate their milestones and provide guidance on any concerns you may have. You can use our app to locate the top 3 nearest healthcare facilities that offer developmental milestone evaluations for children.");
        vaccinationVisit.setAdditionalQuestion(" Vaccinations are an important part of keeping your child healthy and protected from serious diseases. Your child's healthcare provider can recommend and administer the appropriate vaccinations based on their age and medical history. You can use our app to locate the top 3 nearest healthcare facilities that offer children vaccination checkups.");

        TreeNode injuryAccident = new TreeNode("Injury or Accident");
        TreeNode difficultyPain = new TreeNode("Difficulty breathing or chest pain");
        TreeNode allergicReaction = new TreeNode("Severe allergic reaction");
        TreeNode poisoningOverdose = new TreeNode("Poisoning or Overdose");
        TreeNode other3 = new TreeNode("Other");

        emergencyCare.addChild(injuryAccident);
        emergencyCare.addChild(difficultyPain);
        emergencyCare.addChild(allergicReaction);
        emergencyCare.addChild(poisoningOverdose);
        emergencyCare.addChild(other3);

        injuryAccident.setAdditionalQuestion(" If you've been injured or are experiencing an accident, it's important to seek emergency medical attention right away. Depending on the severity of the injury, you may need immediate medical care to prevent further complications. You can use our app to locate the top 3 nearest healthcare facilities that offer emergency care.");
        difficultyPain.setAdditionalQuestion("If you're experiencing difficulty breathing or chest pain, it could be a sign of a serious medical emergency, such as a heart attack or stroke. It's important to seek emergency medical attention right away to get the care you need. You can use our app to locate the top 3 nearest healthcare facilities that offer emergency care.");
        allergicReaction.setAdditionalQuestion(" If you're experiencing a severe allergic reaction, such as difficulty breathing or swelling of the face and throat, it's important to seek emergency medical attention right away. Allergic reactions can be life-threatening if left untreated. You can use our app to locate the top 3 nearest healthcare facilities that offer emergency care");
        poisoningOverdose.setAdditionalQuestion(" If you or someone you know has been poisoned or has overdosed on drugs or medication, it's important to seek emergency medical attention right away. Depending on the substance involved, immediate medical care may be necessary to prevent serious complications or even death. You can use our app to locate the top 3 nearest healthcare facilities that offer emergency care");
        other3.setAdditionalQuestion(" If your emergency doesn't fit into one of the above categories, it's still important to seek medical attention right away. You can use our app to locate the top 3 nearest healthcare facilities that offer emergency care for a wide range of medical emergencies.");

        TreeNode routineVisit2 = new TreeNode("Routine Visit");
        TreeNode healthConcern = new TreeNode("Health concern or Illness");
        TreeNode healthScreening = new TreeNode("Health Screening");

        generalCheckUp.addChild(routineVisit2);
        generalCheckUp.addChild(healthConcern);
        generalCheckUp.addChild(healthScreening);

        routineVisit.setAdditionalQuestion("Regular checkups are important for your overall health and wellbeing. During a routine visit, your healthcare provider will perform a physical exam, check your health status and provide health education as required. You can use our app to locate the top 3 nearest healthcare facilities that offer general checkups.");
        healthConcern.setAdditionalQuestion("If you have a health concern or are ill, it's important to seek medical attention right away. Your healthcare provider can assess your symptoms and determine the best course of treatment. You can use our app to locate the top 3 nearest healthcare facilities that offer general checkups for health concerns or illnesses.");
        healthScreening.setAdditionalQuestion(" Health screening is important to detect and prevent illnesses at early stages. Your healthcare provider can recommend and perform various screening tests based on your age and medical history. You can use our app to locate the top 3 nearest healthcare facilities that offer general checkups for health screening.");


        TreeNode firstPrenatal = new TreeNode("First prenatal visit");
        TreeNode pregnancyCheck = new TreeNode("Pregnancy Check-up");
        TreeNode ultrasoundTest = new TreeNode("Ultrasound or Prenatal testing");
        TreeNode pregnancyConcern = new TreeNode("Pregnancy complication or concern");

        prenatalCheckUp.addChild(firstPrenatal);
        prenatalCheckUp.addChild(pregnancyCheck);
        prenatalCheckUp.addChild(ultrasoundTest);
        prenatalCheckUp.addChild(pregnancyConcern);

        firstPrenatal.setAdditionalQuestion(" It's important to schedule your first prenatal visit as soon as you find out you're pregnant. During this visit, your healthcare provider will perform a physical exam, discuss your medical history, and provide guidance on prenatal care. You can use our app to locate the top 3 nearest healthcare facilities that offer prenatal checkups for first-time visits.");
        pregnancyCheck.setAdditionalQuestion("Regular prenatal checkups are important for monitoring your health and the health of your growing baby. Your healthcare provider will perform a physical exam, check the baby's growth and development, and discuss any concerns you may have. You can use our app to locate the top 3 nearest healthcare facilities that offer prenatal checkups for routine visits.");
        ultrasoundTest.setAdditionalQuestion("Ultrasound and other prenatal tests can provide important information about your baby's health and development. Your healthcare provider may recommend these tests at various stages of your pregnancy. You can use our app to locate the top 3 nearest healthcare facilities that offer prenatal checkups for ultrasound or prenatal testing.");
        pregnancyConcern.setAdditionalQuestion("If you're experiencing a complication or concern during your pregnancy, it's important to seek medical attention right away. Your healthcare provider can assess the situation and determine the best course of action. You can use our app to locate the top 3 nearest healthcare facilities that offer prenatal checkups for pregnancy complications or concerns.");

        medicalServices.addChild(animalBiteVaccine);
        medicalServices.addChild(antiTetanus);
        medicalServices.addChild(childrenCheckUp);
        medicalServices.addChild(emergencyCare);
        medicalServices.addChild(generalCheckUp);
        medicalServices.addChild(prenatalCheckUp);

        root.addChild(specializedDoctors);
        root.addChild(medicalServices);
        root.addChild(justBrowsing);

        return root;
    }
}