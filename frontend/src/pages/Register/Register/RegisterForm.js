import React from 'react';
import { withFormik } from 'formik';
import * as yup from 'yup';
import { omit as _omit } from 'lodash';

const InnerForm = ({ values, errors, touched, handleChange, handleBlur, handleSubmit }) => (
    <form onSubmit={handleSubmit}>
        <div>
            name:
            <input
                type="text"
                name="name"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.name}
            />
            {touched.name && errors.name && errors.name}
        </div>
        <div>
            lastname:
            <input
                type="text"
                name="lastName"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.lastName}
            />
            {touched.lastName && errors.lastName && errors.lastName}
        </div>
        <div>
            email:
            <input
                type="email"
                name="email"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.email}
            />
            {touched.email && errors.email && errors.email}
        </div>
        <div>
            city:
            <input
                type="text"
                name="city"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.city}
            />
            {touched.city && errors.city && errors.city}
        </div>
        <div>
            password:
            <input
                type="password"
                name="password"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.password}
            />
            {touched.password && errors.password && errors.password}
        </div>
        <div>
            repeat password:
            <input
                type="password"
                name="repeatPassword"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.repeatPassword}
            />
            {touched.repeatPassword && errors.repeatPassword && errors.repeatPassword}
        </div>
        <div>
            type:
            <input
                type="text"
                name="userType"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.userType}
            />
            {touched.userType && errors.userType && errors.userType}
        </div>
        <button type="submit">Submit</button>
    </form>
);

const schema = yup.object().shape({
    name: yup
        .string()
        .required('required')
        .min(2)
        .max(100),
    lastName: yup
        .string()
        .required('required')
        .min(2)
        .max(100),
    city: yup
        .string()
        .required('required')
        .min(4)
        .max(100),
    email: yup
        .string()
        .email('Invalid email address')
        .required('required'),
    password: yup
        .string()
        .required('required')
        .min(8),
    repeatPassword: yup
        .string()
        .oneOf([yup.ref('password')], 'Passwords must be the same')
        .required('required'),
    userType: yup.string().required(),
});

export const RegisterForm = withFormik({
    mapPropsToValues: () => ({
        name: 'assd',
        lastName: 'asdads',
        city: 'asdads',
        email: 'asd@o2.pl',
        password: '12345678',
        repeatPassword: '12345678',
        userType: 'coach',
    }),
    validationSchema: schema,
    handleSubmit: (values, { props }) => {
        props.onSubmit(_omit(values, 'repeatPassword'));
    },
})(InnerForm);
