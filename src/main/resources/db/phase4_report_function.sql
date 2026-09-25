CREATE OR REPLACE FUNCTION get_patient_encounter_count (
    p_patient_id IN encounter.patient_id%TYPE
) RETURN NUMBER
IS
    v_count NUMBER;
BEGIN
SELECT COUNT(*)
INTO v_count
FROM encounter
WHERE patient_id = p_patient_id;

RETURN v_count;
END get_patient_encounter_count;