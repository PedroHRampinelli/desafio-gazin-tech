import React from 'react';
import { useFormik } from 'formik';
import FormNivel from './form/form-nivel';
import { createNivel } from '../service';

const Nivel = () => {
    const formik = useFormik({
        initialValues: {
            nivel: {
                id: 0,
                nivel: ''
            }
        },
        onSubmit: values => {
            createNivel(values);
        },
    });

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12 mt-3">
                    <h2>Cadastro de Nível</h2>
                </div>
                
                <div className="col-md-12">
                    <FormNivel formik={formik}/>
                </div>

            </div>
        </div>
    )
}
export default Nivel;