import React, { useEffect, useState } from 'react';
import { useFormik } from 'formik';
import { createDesenvolvedor, findAllNiveis } from '../service';
import Form from './form/form';

const Desenvolvedor = () => {
    const [niveis, setNiveis] = useState([]);

    const findNiveis = async () => {
        const response = await findAllNiveis();

        setNiveis(response);
    }

    useEffect(() => {
        findNiveis();
    }, [])

    const formik = useFormik({
        initialValues: {
            nome: '',
            sexo: 'M',
            dataNascimento: '',
            hobby: '',
            nivelId: 0
        },
        onSubmit: values => {
            createDesenvolvedor(values);
        },
    });

    useEffect(() => {
        if (niveis.length > 0) {
            formik.setFieldValue("nivelId", niveis[0].id);
        }
    }, [niveis]);

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12 mt-3">
                    <h2>Cadastro de Desenvolvedor</h2>
                </div>
                
                <div className="col-md-12">
                    <Form formik={formik} niveis={niveis} />
                </div>

            </div>
        </div>
    )
}
export default Desenvolvedor;