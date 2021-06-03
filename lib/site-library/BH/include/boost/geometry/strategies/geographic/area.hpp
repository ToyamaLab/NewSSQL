// Boost.Geometry (aka GGL, Generic Geometry Library)

<<<<<<< HEAD
// Copyright (c) 2016-2017 Oracle and/or its affiliates.
=======
// Copyright (c) 2017 Adam Wulkiewicz, Lodz, Poland.

// Copyright (c) 2016-2018 Oracle and/or its affiliates.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// Contributed and/or modified by Vissarion Fisikopoulos, on behalf of Oracle
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_STRATEGIES_GEOGRAPHIC_AREA_HPP
#define BOOST_GEOMETRY_STRATEGIES_GEOGRAPHIC_AREA_HPP


<<<<<<< HEAD
#include <boost/geometry/core/srs.hpp>
=======
#include <boost/geometry/srs/spheroid.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#include <boost/geometry/formulas/area_formulas.hpp>
#include <boost/geometry/formulas/authalic_radius_sqr.hpp>
#include <boost/geometry/formulas/eccentricity_sqr.hpp>

<<<<<<< HEAD
=======
#include <boost/geometry/strategies/area.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/geometry/strategies/geographic/parameters.hpp>


namespace boost { namespace geometry
{

namespace strategy { namespace area
{

/*!
\brief Geographic area calculation
\ingroup strategies
\details Geographic area calculation by trapezoidal rule plus integral
         approximation that gives the ellipsoidal correction
<<<<<<< HEAD
\tparam PointOfSegment \tparam_segment_point
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
\tparam FormulaPolicy Formula used to calculate azimuths
\tparam SeriesOrder The order of approximation of the geodesic integral
\tparam Spheroid The spheroid model
\tparam CalculationType \tparam_calculation
\author See
- Danielsen JS, The area under the geodesic. Surv Rev 30(232): 61–66, 1989
- Charles F.F Karney, Algorithms for geodesics, 2011 https://arxiv.org/pdf/1109.4448.pdf

\qbk{
[heading See also]
<<<<<<< HEAD
[link geometry.reference.algorithms.area.area_2_with_strategy area (with strategy)]
=======
\* [link geometry.reference.algorithms.area.area_2_with_strategy area (with strategy)]
\* [link geometry.reference.srs.srs_spheroid srs::spheroid]
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}
*/
template
<
<<<<<<< HEAD
    typename PointOfSegment,
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typename FormulaPolicy = strategy::andoyer,
    std::size_t SeriesOrder = strategy::default_order<FormulaPolicy>::value,
    typename Spheroid = srs::spheroid<double>,
    typename CalculationType = void
>
class geographic
{
    // Switch between two kinds of approximation(series in eps and n v.s.series in k ^ 2 and e'^2)
    static const bool ExpandEpsN = true;
    // LongSegment Enables special handling of long segments
    static const bool LongSegment = false;

    //Select default types in case they are not set

<<<<<<< HEAD
    typedef typename boost::mpl::if_c
    <
        boost::is_void<CalculationType>::type::value,
        typename select_most_precise
            <
                typename coordinate_type<PointOfSegment>::type,
                double
            >::type,
        CalculationType
    >::type CT;
=======
public:
    template <typename Geometry>
    struct result_type
        : strategy::area::detail::result_type
            <
                Geometry,
                CalculationType
            >
    {};
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

protected :
    struct spheroid_constants
    {
<<<<<<< HEAD
        Spheroid m_spheroid;
        CT const m_a2;  // squared equatorial radius
        CT const m_e2;  // squared eccentricity
        CT const m_ep2; // squared second eccentricity
        CT const m_ep;  // second eccentricity
        CT const m_c2;  // squared authalic radius
=======
        typedef typename boost::mpl::if_c
            <
                boost::is_void<CalculationType>::value,
                typename geometry::radius_type<Spheroid>::type,
                CalculationType
            >::type calc_t;

        Spheroid m_spheroid;
        calc_t const m_a2;  // squared equatorial radius
        calc_t const m_e2;  // squared eccentricity
        calc_t const m_ep2; // squared second eccentricity
        calc_t const m_ep;  // second eccentricity
        calc_t const m_c2;  // squared authalic radius
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

        inline spheroid_constants(Spheroid const& spheroid)
            : m_spheroid(spheroid)
            , m_a2(math::sqr(get_radius<0>(spheroid)))
<<<<<<< HEAD
            , m_e2(formula::eccentricity_sqr<CT>(spheroid))
            , m_ep2(m_e2 / (CT(1.0) - m_e2))
            , m_ep(math::sqrt(m_ep2))
            , m_c2(formula_dispatch::authalic_radius_sqr
                    <
                        CT, Spheroid, srs_spheroid_tag
=======
            , m_e2(formula::eccentricity_sqr<calc_t>(spheroid))
            , m_ep2(m_e2 / (calc_t(1.0) - m_e2))
            , m_ep(math::sqrt(m_ep2))
            , m_c2(formula_dispatch::authalic_radius_sqr
                    <
                        calc_t, Spheroid, srs_spheroid_tag
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                    >::apply(m_a2, m_e2))
        {}
    };

<<<<<<< HEAD
    struct area_sums
    {
        CT m_excess_sum;
        CT m_correction_sum;

        // Keep track if encircles some pole
        std::size_t m_crosses_prime_meridian;

        inline area_sums()
=======
public:
    template <typename Geometry>
    class state
    {
        friend class geographic;

        typedef typename result_type<Geometry>::type return_type;

    public:
        inline state()
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            : m_excess_sum(0)
            , m_correction_sum(0)
            , m_crosses_prime_meridian(0)
        {}
<<<<<<< HEAD
        inline CT area(spheroid_constants spheroid_const) const
        {
            CT result;

            CT sum = spheroid_const.m_c2 * m_excess_sum
=======

    private:
        inline return_type area(spheroid_constants const& spheroid_const) const
        {
            return_type result;

            return_type sum = spheroid_const.m_c2 * m_excess_sum
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                   + spheroid_const.m_e2 * spheroid_const.m_a2 * m_correction_sum;

            // If encircles some pole
            if (m_crosses_prime_meridian % 2 == 1)
            {
                std::size_t times_crosses_prime_meridian
                        = 1 + (m_crosses_prime_meridian / 2);

<<<<<<< HEAD
                result = CT(2.0)
                         * geometry::math::pi<CT>()
                         * spheroid_const.m_c2
                         * CT(times_crosses_prime_meridian)
                         - geometry::math::abs(sum);

                if (geometry::math::sign<CT>(sum) == 1)
=======
                result = return_type(2.0)
                         * geometry::math::pi<return_type>()
                         * spheroid_const.m_c2
                         * return_type(times_crosses_prime_meridian)
                         - geometry::math::abs(sum);

                if (geometry::math::sign<return_type>(sum) == 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                {
                    result = - result;
                }

            }
            else
            {
                result = sum;
            }

            return result;
        }
<<<<<<< HEAD
    };

public :
    typedef CT return_type;
    typedef PointOfSegment segment_point_type;
    typedef area_sums state_type;

=======

        return_type m_excess_sum;
        return_type m_correction_sum;

        // Keep track if encircles some pole
        std::size_t m_crosses_prime_meridian;
    };

public :
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    explicit inline geographic(Spheroid const& spheroid = Spheroid())
        : m_spheroid_constants(spheroid)
    {}

<<<<<<< HEAD
    inline void apply(PointOfSegment const& p1,
                      PointOfSegment const& p2,
                      area_sums& state) const
    {

        if (! geometry::math::equals(get<0>(p1), get<0>(p2)))
        {

            typedef geometry::formula::area_formulas
                <
                    CT, SeriesOrder, ExpandEpsN
=======
    template <typename PointOfSegment, typename Geometry>
    inline void apply(PointOfSegment const& p1,
                      PointOfSegment const& p2,
                      state<Geometry>& st) const
    {
        if (! geometry::math::equals(get<0>(p1), get<0>(p2)))
        {
            typedef geometry::formula::area_formulas
                <
                    typename result_type<Geometry>::type,
                    SeriesOrder, ExpandEpsN
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                > area_formulas;

            typename area_formulas::return_type_ellipsoidal result =
                     area_formulas::template ellipsoidal<FormulaPolicy::template inverse>
                                             (p1, p2, m_spheroid_constants);

<<<<<<< HEAD
            state.m_excess_sum += result.spherical_term;
            state.m_correction_sum += result.ellipsoidal_term;
=======
            st.m_excess_sum += result.spherical_term;
            st.m_correction_sum += result.ellipsoidal_term;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

            // Keep track whenever a segment crosses the prime meridian
            if (area_formulas::crosses_prime_meridian(p1, p2))
            {
<<<<<<< HEAD
                state.m_crosses_prime_meridian++;
=======
                st.m_crosses_prime_meridian++;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }
        }
    }

<<<<<<< HEAD
    inline return_type result(area_sums const& state) const
    {
        return state.area(m_spheroid_constants);
=======
    template <typename Geometry>
    inline typename result_type<Geometry>::type
        result(state<Geometry> const& st) const
    {
        return st.area(m_spheroid_constants);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }

private:
    spheroid_constants m_spheroid_constants;

};

#ifndef DOXYGEN_NO_STRATEGY_SPECIALIZATIONS

namespace services
{


<<<<<<< HEAD
template <typename Point>
struct default_strategy<geographic_tag, Point>
{
    typedef strategy::area::geographic<Point> type;
=======
template <>
struct default_strategy<geographic_tag>
{
    typedef strategy::area::geographic<> type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

#endif // DOXYGEN_NO_STRATEGY_SPECIALIZATIONS

}

}} // namespace strategy::area




}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_STRATEGIES_GEOGRAPHIC_AREA_HPP
