import React from 'react';
import { Form, Field, withFormik } from 'formik';
import * as yup from 'yup';

const InnerForm = ({ handleSubmit }) => (
    <Form>
        <div>
            <Field type="text" name="username" />
        </div>

        <div>
            <Field type="password" name="password" />
        </div>
        <button type="submit">Zaloguj</button>
    </Form>
);

const schema = yup.object().shape({
    username: yup
        .string()
        .email()
        .required(),
    password: yup.string().required(),
});

export const LoginForm = withFormik({
    mapPropsToValues: () => ({
        username: 'm@m.mm',
        password: '111111',
    }),
    validationSchema: schema,
    handleSubmit: (values, { props }) => {
        props.onSubmit(values);
    },
})(InnerForm);
